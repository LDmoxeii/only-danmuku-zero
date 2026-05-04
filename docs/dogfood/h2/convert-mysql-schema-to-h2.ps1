param(
    [Parameter(Mandatory = $true)]
    [string] $InputPath,

    [Parameter(Mandatory = $true)]
    [string] $OutputPath
)

function Clean-AnnotationText {
    param([string] $Value)

    $cleaned = $Value
    $cleaned = $cleaned -replace '@(?:Spe|Specification|Spec|Fac|Factory|I)(?:=[^;''"]*)?;?', ''
    $cleaned = $cleaned -replace '@SoftDeleteColumn=[A-Za-z_][A-Za-z0-9_]*(?:;)?', ''
    $cleaned = $cleaned -replace '@Version=true', '@Version'
    $cleaned = $cleaned -replace '@T=([A-Za-z_][A-Za-z0-9_]*)\?', '@T=$1'
    $cleaned = $cleaned -replace '\s*;+\s*$', ''
    return $cleaned.Trim()
}

function Clean-SqlFragment {
    param([string] $Line)

    $line = $Line
    $line = $line -replace '\s+CHARACTER SET\s+\S+', ''
    $line = $line -replace '\s+COLLATE\s+\S+', ''
    $line = $line -replace '\s+AUTO_INCREMENT', ''
    $line = $line -replace '\s+USING BTREE', ''
    $line = $line -replace '\s+ON UPDATE CURRENT_TIMESTAMP(?:\(\d+\))?', ''
    $line = $line -replace '\bdatetime\(\d+\)', 'datetime'
    $line = $line -replace '\bCURRENT_TIMESTAMP\(\d+\)', 'CURRENT_TIMESTAMP'
    $line = $line -replace '\s+unsigned\b', ''
    $line = Clean-AnnotationText $line
    return $line
}

function Extract-TableCommentRaw {
    param([string] $Line)

    $match = [regex]::Match($Line, "COMMENT='((?:[^'\\]|\\.)*)'")
    if (-not $match.Success) {
        return ""
    }

    return $match.Groups[1].Value
}

function Extract-TableComment {
    param([string] $Line)

    return Clean-AnnotationText (Extract-TableCommentRaw $Line)
}

function Extract-LegacySoftDeleteColumn {
    param([string] $TableComment)

    $match = [regex]::Match($TableComment, '@SoftDeleteColumn=([A-Za-z_][A-Za-z0-9_]*)')
    if (-not $match.Success) {
        return ""
    }
    return $match.Groups[1].Value
}

$lines = Get-Content -Path $InputPath -Encoding UTF8
$output = New-Object System.Collections.Generic.List[string]
$output.Add("-- Generated from only_danmuku MySQL schema for cap4k pipeline dogfood.")
$output.Add("-- Compatibility-only blockers are intentionally normalized here; see issue backlog.")
$output.Add("")

$currentTable = $null
$currentBody = New-Object System.Collections.Generic.List[string]
$skippingTable = $false

foreach ($rawLine in $lines) {
    $line = $rawLine.TrimEnd()

    if ($line -match '^CREATE TABLE `([^`]+)` \($') {
        $currentTable = $Matches[1]
        $currentBody.Clear()
        $skippingTable = $currentTable.StartsWith("__")
        continue
    }

    if ($null -eq $currentTable) {
        continue
    }

    if ($line -match '^\) ENGINE=') {
        if (-not $skippingTable) {
            $legacySoftDeleteColumn = Extract-LegacySoftDeleteColumn (Extract-TableCommentRaw $line)
            $softDeleteColumnPattern = if ($legacySoftDeleteColumn.Length -gt 0) {
                '^\s*`' + [regex]::Escape($legacySoftDeleteColumn) + '`\s+'
            } else {
                $null
            }
            $defaultDeletedColumnPattern = '^\s*`deleted`\s+'

            $body = $currentBody |
                Where-Object { $_.Trim().Length -gt 0 } |
                ForEach-Object { Clean-SqlFragment $_ } |
                Where-Object {
                    $trimmed = $_.TrimStart()
                    -not ($trimmed -match '^KEY `') -and
                    -not ($trimmed -match '^FULLTEXT KEY `') -and
                    -not ($trimmed -match '^SPATIAL KEY `')
                } |
                ForEach-Object {
                    $entry = $_.TrimEnd(',')
                    $isSoftDeleteColumn =
                        ($entry -match $defaultDeletedColumnPattern) -or
                            ($softDeleteColumnPattern -and $entry -match $softDeleteColumnPattern)
                    if ($isSoftDeleteColumn -and $entry -notmatch '@Deleted(?:[;''"]|$)') {
                        $commentMatch = [regex]::Match($entry, "COMMENT '([^']*)'")
                        if ($commentMatch.Success) {
                            $commentValue = $commentMatch.Groups[1].Value
                            $entry = $entry.Replace($commentMatch.Value, "COMMENT '$commentValue;@Deleted'")
                        }
                    }
                    if ($entry -match '^UNIQUE KEY `([^`]+)` \((.+)\)$') {
                        "  CONSTRAINT ``${currentTable}_$($Matches[1])`` UNIQUE ($($Matches[2]))"
                    } else {
                        $entry
                    }
                }

            $bodyArray = @($body)
            if ($bodyArray.Count -gt 0) {
                $output.Add("CREATE TABLE IF NOT EXISTS ``$currentTable`` (")
                for ($i = 0; $i -lt $bodyArray.Count; $i++) {
                    $suffix = if ($i -lt $bodyArray.Count - 1) { "," } else { "" }
                    $output.Add("$($bodyArray[$i])$suffix")
                }
                $output.Add(");")

                $tableComment = Extract-TableComment $line
                if ($tableComment.Length -gt 0) {
                    $escapedComment = $tableComment -replace "'", "''"
                    $output.Add("COMMENT ON TABLE ``$currentTable`` IS '$escapedComment';")
                }
                $output.Add("")
            }
        }

        $currentTable = $null
        $skippingTable = $false
        continue
    }

    if (-not $skippingTable) {
        $currentBody.Add($line)
    }
}

$parent = Split-Path -Parent $OutputPath
if ($parent -and -not (Test-Path $parent)) {
    New-Item -ItemType Directory -Path $parent | Out-Null
}

Set-Content -Path $OutputPath -Value $output -Encoding UTF8
