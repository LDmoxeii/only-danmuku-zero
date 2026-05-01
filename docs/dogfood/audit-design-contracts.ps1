$ErrorActionPreference = "Stop"

if ($PSVersionTable.PSVersion.Major -lt 7) {
    throw "audit-design-contracts.ps1 requires PowerShell 7+ (pwsh) for stable UTF-8 JSON formatting."
}

$projectRoot = Resolve-Path (Join-Path $PSScriptRoot "..\..")
$designFile = Join-Path $projectRoot "codegen\design\design.json"
$generatedRoot = Join-Path $projectRoot "only-danmuku-application\build\generated\cap4k\main\kotlin"
$sourceRoot = Join-Path $projectRoot "only-danmuku-application\src\main\kotlin"
$jsonReportFile = Join-Path $projectRoot "docs\dogfood\cap4k-pipeline-contract-audit.json"
$markdownReportFile = Join-Path $projectRoot "docs\dogfood\cap4k-pipeline-contract-audit.md"

$contractTags = @("command", "query", "client")

$contractLayouts = @{
    "command" = [pscustomobject]@{
        RootPackage = "edu.only4.danmuku.application.commands"
        Suffix = "Cmd"
    }
    "query" = [pscustomobject]@{
        RootPackage = "edu.only4.danmuku.application.queries"
        Suffix = "Qry"
    }
    "client" = [pscustomobject]@{
        RootPackage = "edu.only4.danmuku.application.distributed.clients"
        Suffix = "Cli"
    }
}

$legacyMarkers = @(
    "ListQueryParam<",
    "PageQueryParam<",
    "ListQuery<",
    "PageQuery<",
    "data class Item",
    "class Item",
    ".Item"
)

function Read-DesignEntries {
    if (-not (Test-Path -LiteralPath $designFile)) {
        throw "Design input not found: $designFile"
    }
    return @(Get-Content -LiteralPath $designFile -Raw | ConvertFrom-Json -Depth 100)
}

function Convert-PackageToPath([string] $packageName) {
    if ([string]::IsNullOrWhiteSpace($packageName)) {
        return ""
    }
    return $packageName.Trim(".").Replace(".", [System.IO.Path]::DirectorySeparatorChar)
}

function Get-JsonString($obj, [string] $name) {
    $property = $obj.PSObject.Properties[$name]
    if ($null -eq $property -or $null -eq $property.Value) {
        return ""
    }
    return [string] $property.Value
}

function Join-Package([string] $rootPackage, [string] $relativePackage) {
    $root = $rootPackage.Trim(".")
    $relative = $relativePackage.Trim(".")
    if ([string]::IsNullOrWhiteSpace($relative)) {
        return $root
    }
    return "$root.$relative"
}

function Join-KotlinFilePath([string] $root, [string] $packageName, [string] $typeName) {
    $packagePath = Convert-PackageToPath $packageName
    if ([string]::IsNullOrWhiteSpace($packagePath)) {
        return Join-Path $root "$typeName.kt"
    }
    return Join-Path (Join-Path $root $packagePath) "$typeName.kt"
}

function New-ExpectedContract($entry) {
    $tag = Get-JsonString $entry "tag"
    $name = Get-JsonString $entry "name"
    $relativePackage = Get-JsonString $entry "package"
    $layout = $contractLayouts[$tag]
    $typeName = "$name$($layout.Suffix)"
    $packageName = Join-Package $layout.RootPackage $relativePackage

    return [pscustomobject]@{
        Tag = $tag
        DesignName = $name
        PackageName = $packageName
        TypeName = $typeName
        GeneratedPath = Join-KotlinFilePath $generatedRoot $packageName $typeName
        SourcePath = Join-KotlinFilePath $sourceRoot $packageName $typeName
    }
}

function Find-LegacyMarkers([string] $path) {
    if ([string]::IsNullOrWhiteSpace($path) -or -not (Test-Path -LiteralPath $path)) {
        return @()
    }
    $content = Get-Content -LiteralPath $path -Raw
    return @($legacyMarkers | Where-Object { $content.Contains($_) })
}

function Test-ExpectedContract($expected) {
    $generatedExists = Test-Path -LiteralPath $expected.GeneratedPath
    $sourceExists = Test-Path -LiteralPath $expected.SourcePath
    $scanPath = $null
    if ($generatedExists) {
        $scanPath = $expected.GeneratedPath
    } elseif ($sourceExists) {
        $scanPath = $expected.SourcePath
    }

    $legacyMarkersFound = Find-LegacyMarkers $scanPath

    $status = "MISSING_CONTRACT"
    if ($sourceExists) {
        $status = "CHECKED_IN_CONTRACT"
    } elseif ($generatedExists -and $legacyMarkersFound.Count -gt 0) {
        $status = "GENERATED_WITH_LEGACY_MARKER"
    } elseif ($generatedExists) {
        $status = "GENERATED"
    }

    return [pscustomobject]@{
        tag = $expected.Tag
        designName = $expected.DesignName
        packageName = $expected.PackageName
        typeName = $expected.TypeName
        status = $status
        generatedPath = $expected.GeneratedPath
        sourcePath = $expected.SourcePath
        generatedExists = $generatedExists
        sourceExists = $sourceExists
        legacyMarkers = @($legacyMarkersFound)
    }
}

function Invoke-ContractAudit {
    $entries = Read-DesignEntries
    $contractEntries = @($entries | Where-Object {
        $tag = Get-JsonString $_ "tag"
        $contractTags -contains $tag
    })

    return @($contractEntries | ForEach-Object {
        Test-ExpectedContract (New-ExpectedContract $_)
    })
}

function Convert-ToProjectRelativePath([string] $path) {
    if ([string]::IsNullOrWhiteSpace($path)) {
        return ""
    }
    $root = [string] $projectRoot
    if ($path.StartsWith($root, [System.StringComparison]::OrdinalIgnoreCase)) {
        return $path.Substring($root.Length).TrimStart("\", "/")
    }
    return $path
}

function Convert-MarkdownCell([string] $text) {
    if ($null -eq $text) {
        return ""
    }
    return $text.Replace("|", "\|")
}

function New-AuditSummary($results) {
    $total = @($results).Count
    $generated = @($results | Where-Object { $_.status -eq "GENERATED" }).Count
    $checkedIn = @($results | Where-Object { $_.status -eq "CHECKED_IN_CONTRACT" }).Count
    $missing = @($results | Where-Object { $_.status -eq "MISSING_CONTRACT" }).Count
    $legacy = @($results | Where-Object { $_.status -eq "GENERATED_WITH_LEGACY_MARKER" }).Count
    return [pscustomobject]@{
        total = $total
        generated = $generated
        checkedIn = $checkedIn
        missing = $missing
        generatedWithLegacyMarker = $legacy
        failures = $checkedIn + $missing + $legacy
    }
}

function Write-AuditJson($report) {
    $json = $report | ConvertTo-Json -Depth 20
    Set-Content -LiteralPath $jsonReportFile -Value $json -Encoding utf8NoBOM
}

function Write-AuditMarkdown($report) {
    $lines = [System.Collections.Generic.List[string]]::new()
    $lines.Add("# Cap4k Pipeline Contract Audit")
    $lines.Add("")
    $lines.Add("Generated at: ``$($report.generatedAt)``")
    $lines.Add("")
    $lines.Add("## Summary")
    $lines.Add("")
    $lines.Add("| Metric | Count |")
    $lines.Add("| --- | ---: |")
    $lines.Add("| Total Query/Cmd/Cli contracts | $($report.summary.total) |")
    $lines.Add("| Generated contracts | $($report.summary.generated) |")
    $lines.Add("| Checked-in contract drift | $($report.summary.checkedIn) |")
    $lines.Add("| Missing contracts | $($report.summary.missing) |")
    $lines.Add("| Generated files with legacy markers | $($report.summary.generatedWithLegacyMarker) |")
    $lines.Add("| Failures | $($report.summary.failures) |")
    $lines.Add("")
    $lines.Add("## Failures")
    $lines.Add("")

    $failures = @($report.results | Where-Object { $_.status -ne "GENERATED" } | Sort-Object tag, packageName, typeName)
    if ($failures.Count -eq 0) {
        $lines.Add("No contract drift detected.")
    } else {
        $lines.Add("| Status | Tag | Type | Generated Path | Source Path | Legacy Markers |")
        $lines.Add("| --- | --- | --- | --- | --- | --- |")
        foreach ($failure in $failures) {
            $markers = ($failure.legacyMarkers -join ", ")
            $lines.Add("| $(Convert-MarkdownCell $failure.status) | $(Convert-MarkdownCell $failure.tag) | $(Convert-MarkdownCell $failure.typeName) | $(Convert-MarkdownCell (Convert-ToProjectRelativePath $failure.generatedPath)) | $(Convert-MarkdownCell (Convert-ToProjectRelativePath $failure.sourcePath)) | $(Convert-MarkdownCell $markers) |")
        }
    }

    Set-Content -LiteralPath $markdownReportFile -Value $lines -Encoding utf8NoBOM
}

$results = Invoke-ContractAudit
$summary = New-AuditSummary $results
$report = [pscustomobject]@{
    generatedAt = (Get-Date).ToString("o")
    designFile = Convert-ToProjectRelativePath $designFile
    generatedRoot = Convert-ToProjectRelativePath $generatedRoot
    sourceRoot = Convert-ToProjectRelativePath $sourceRoot
    summary = $summary
    results = @($results)
}

Write-AuditJson $report
Write-AuditMarkdown $report

if ($summary.failures -gt 0) {
    Write-Error "Design contract audit failed with $($summary.failures) drift item(s). See $markdownReportFile"
}

Write-Host "Design contract audit passed. See $markdownReportFile"
