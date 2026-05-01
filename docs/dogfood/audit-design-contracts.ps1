$ErrorActionPreference = "Stop"

if ($PSVersionTable.PSVersion.Major -lt 7) {
    throw "audit-design-contracts.ps1 requires PowerShell 7+ (pwsh)."
}

$projectRoot = (Resolve-Path (Join-Path $PSScriptRoot "..\..")).Path
$designFile = Join-Path $projectRoot "codegen\design\design.json"
$planFile = Join-Path $projectRoot "build\cap4k\plan.json"
$jsonReportFile = Join-Path $projectRoot "docs\dogfood\cap4k-pipeline-contract-audit.json"
$markdownReportFile = Join-Path $projectRoot "docs\dogfood\cap4k-pipeline-contract-audit.md"

$contractSpecs = @{
    command = @{ GeneratorId = "design-command"; Suffix = "Cmd" }
    query = @{ GeneratorId = "design-query"; Suffix = "Qry" }
    client = @{ GeneratorId = "design-client"; Suffix = "Cli" }
}

$blockingLegacyMarkers = @(
    "ListQueryParam<",
    "PageQueryParam<",
    "ListQuery<",
    "PageQuery<"
)

function Read-JsonFile([string] $path) {
    if (-not (Test-Path -LiteralPath $path)) {
        throw "Required file not found: $path"
    }
    return Get-Content -LiteralPath $path -Raw | ConvertFrom-Json -Depth 100
}

function Get-JsonString($obj, [string] $name) {
    if ($null -eq $obj) {
        return ""
    }
    $property = $obj.PSObject.Properties[$name]
    if ($null -eq $property -or $null -eq $property.Value) {
        return ""
    }
    return [string] $property.Value
}

function Convert-ToProjectRelativePath([string] $path) {
    if ([string]::IsNullOrWhiteSpace($path)) {
        return ""
    }
    $fullPath = if ([System.IO.Path]::IsPathRooted($path)) {
        [System.IO.Path]::GetFullPath($path)
    } else {
        [System.IO.Path]::GetFullPath((Join-Path $projectRoot $path))
    }
    $root = [System.IO.Path]::GetFullPath([string] $projectRoot)
    if ($fullPath.StartsWith($root, [System.StringComparison]::OrdinalIgnoreCase)) {
        return $fullPath.Substring($root.Length).TrimStart("\", "/")
    }
    return $path
}

function Convert-MarkdownCell([string] $text) {
    if ($null -eq $text) {
        return ""
    }
    return $text.Replace("|", "\|")
}

function Get-PlanTag([string] $generatorId) {
    switch ($generatorId) {
        "design-command" { return "command" }
        "design-query" { return "query" }
        "design-client" { return "client" }
        default { return "" }
    }
}

function Convert-ToArray($value) {
    if ($null -eq $value) {
        return @()
    }
    return @($value)
}

function New-ExpectedContract($entry) {
    $tag = Get-JsonString $entry "tag"
    if (-not $contractSpecs.ContainsKey($tag)) {
        return $null
    }
    $name = Get-JsonString $entry "name"
    $typeName = "$name$($contractSpecs[$tag].Suffix)"
    return [pscustomobject]@{
        tag = $tag
        designName = $name
        typeName = $typeName
        key = "$tag|$typeName"
    }
}

function New-PlanIndex($planItems) {
    $index = @{}
    foreach ($item in $planItems) {
        $tag = Get-PlanTag (Get-JsonString $item "generatorId")
        $typeName = Get-JsonString $item.context "typeName"
        if (-not [string]::IsNullOrWhiteSpace($tag) -and -not [string]::IsNullOrWhiteSpace($typeName)) {
            $index["$tag|$typeName"] = $item
        }
    }
    return $index
}

function Find-LegacyMarkers([string] $content) {
    return @($blockingLegacyMarkers | Where-Object { $content.Contains($_) })
}

function Test-PlanContext($item) {
    $issues = [System.Collections.Generic.List[string]]::new()
    if ($null -eq $item.context) {
        $issues.Add("missing context")
        return @($issues)
    }
    foreach ($required in @("packageName", "typeName")) {
        if ([string]::IsNullOrWhiteSpace((Get-JsonString $item.context $required))) {
            $issues.Add("missing context.$required")
        }
    }
    return @($issues)
}

function Test-Fields([string] $content, $fields, [string] $scope, [System.Collections.Generic.List[string]] $issues) {
    foreach ($field in (Convert-ToArray $fields)) {
        $name = Get-JsonString $field "name"
        if (-not [string]::IsNullOrWhiteSpace($name) -and $content -notmatch "(?m)\bval\s+$([regex]::Escape($name))\s*:") {
            $issues.Add("missing $scope field $name")
        }
    }
}

function Test-NestedTypes([string] $content, $nestedTypes, [string] $scope, [System.Collections.Generic.List[string]] $issues) {
    foreach ($nested in (Convert-ToArray $nestedTypes)) {
        $name = Get-JsonString $nested "name"
        if (-not [string]::IsNullOrWhiteSpace($name) -and $content -notmatch "(?m)^\s*data\s+class\s+$([regex]::Escape($name))\b") {
            $issues.Add("missing $scope nested type $name")
        }
        Test-Fields $content $nested.fields "$scope nested $name" $issues
    }
}

function Test-ContractStructure($item, [string] $path) {
    $content = Get-Content -LiteralPath $path -Raw
    $context = $item.context
    $issues = [System.Collections.Generic.List[string]]::new()
    $packageName = Get-JsonString $context "packageName"
    $typeName = Get-JsonString $context "typeName"

    if (-not $content.Contains("package $packageName")) {
        $issues.Add("missing package $packageName")
    }
    if ($content -notmatch "(?m)^\s*object\s+$([regex]::Escape($typeName))\b") {
        $issues.Add("missing object $typeName")
    }
    if ($content -notmatch "(?m)^\s*(data\s+class|class)\s+Request\b") {
        $issues.Add("missing Request type")
    }
    if ($content -notmatch "(?m)^\s*(data\s+class|data\s+object)\s+Response\b") {
        $issues.Add("missing Response type")
    }

    Test-Fields $content $context.requestFields "request" $issues
    Test-Fields $content $context.responseFields "response" $issues
    Test-NestedTypes $content $context.requestNestedTypes "request" $issues
    Test-NestedTypes $content $context.responseNestedTypes "response" $issues

    if ($context.pageRequest -eq $true) {
        foreach ($marker in @("PageRequest", "pageNum", "pageSize")) {
            if (-not $content.Contains($marker)) {
                $issues.Add("missing page request marker $marker")
            }
        }
    }

    return [pscustomobject]@{
        legacyMarkers = Find-LegacyMarkers $content
        structureIssues = @($issues)
    }
}

function Test-ExpectedContract($expected, $planIndex) {
    if (-not $planIndex.ContainsKey($expected.key)) {
        return [pscustomobject]@{
            tag = $expected.tag; designName = $expected.designName; typeName = $expected.typeName
            status = "MISSING_PLAN_ITEM"; outputPath = ""; outputKind = ""; conflictPolicy = ""
            legacyMarkers = @(); structureIssues = @("missing plan item $($expected.key)")
        }
    }

    $item = $planIndex[$expected.key]
    $contextIssues = Test-PlanContext $item
    $outputPath = Get-JsonString $item "outputPath"
    $absoluteOutputPath = Join-Path $projectRoot $outputPath
    $legacyMarkers = @()
    $structureIssues = @($contextIssues)
    $status = "PLANNED_AND_MATCHED"

    if ($contextIssues.Count -gt 0) {
        $status = "PLAN_CONTEXT_DEFECT"
    } elseif (-not (Test-Path -LiteralPath $absoluteOutputPath)) {
        $status = "MISSING_OUTPUT_FILE"
        $structureIssues += "missing output file"
    } else {
        $result = Test-ContractStructure $item $absoluteOutputPath
        $legacyMarkers = @($result.legacyMarkers)
        $structureIssues = @($result.structureIssues)
        if ($legacyMarkers.Count -gt 0) {
            $status = "LEGACY_CONTRACT_MARKER"
        } elseif ($structureIssues.Count -gt 0) {
            $status = "CONTRACT_STRUCTURE_DRIFT"
        }
    }

    return [pscustomobject]@{
        tag = $expected.tag
        designName = $expected.designName
        packageName = Get-JsonString $item.context "packageName"
        typeName = $expected.typeName
        status = $status
        outputPath = Convert-ToProjectRelativePath $outputPath
        outputKind = Get-JsonString $item "outputKind"
        conflictPolicy = Get-JsonString $item "conflictPolicy"
        legacyMarkers = @($legacyMarkers)
        structureIssues = @($structureIssues)
    }
}

$designEntries = Convert-ToArray (Read-JsonFile $designFile)
$planItems = Convert-ToArray ((Read-JsonFile $planFile).items)
$planIndex = New-PlanIndex $planItems
$results = @($designEntries | ForEach-Object { New-ExpectedContract $_ } | Where-Object { $null -ne $_ } | ForEach-Object {
    Test-ExpectedContract $_ $planIndex
} | Sort-Object tag, packageName, typeName)

$statusCounts = [ordered]@{}
foreach ($result in $results) {
    if (-not $statusCounts.Contains($result.status)) {
        $statusCounts[$result.status] = 0
    }
    $statusCounts[$result.status] += 1
}
$failures = @($results | Where-Object { $_.status -ne "PLANNED_AND_MATCHED" })
$summary = [pscustomobject]@{
    total = $results.Count
    plannedAndMatched = @($results | Where-Object { $_.status -eq "PLANNED_AND_MATCHED" }).Count
    failures = $failures.Count
    statuses = $statusCounts
}
$report = [pscustomobject]@{
    designFile = Convert-ToProjectRelativePath $designFile
    planFile = Convert-ToProjectRelativePath $planFile
    summary = $summary
    results = $results
}

$report | ConvertTo-Json -Depth 100 | Set-Content -LiteralPath $jsonReportFile -Encoding utf8NoBOM

$lines = [System.Collections.Generic.List[string]]::new()
$lines.Add("# Cap4k Pipeline Contract Audit")
$lines.Add("")
$lines.Add("Design input: ``$($report.designFile)``")
$lines.Add("")
$lines.Add("Plan input: ``$($report.planFile)``")
$lines.Add("")
$lines.Add('The audit reads planned `outputPath` values from `cap4kPlan`; it does not require contracts to be generated under `build/generated`.')
$lines.Add("")
$lines.Add("## Summary")
$lines.Add("")
$lines.Add("| Metric | Count |")
$lines.Add("| --- | ---: |")
$lines.Add("| Total Query/Cmd/Cli contracts | $($summary.total) |")
$lines.Add("| Planned and matched contracts | $($summary.plannedAndMatched) |")
$lines.Add("| Failures | $($summary.failures) |")
foreach ($status in $statusCounts.Keys) {
    $lines.Add("| $status | $($statusCounts[$status]) |")
}
$lines.Add("")
$lines.Add("## Failures")
$lines.Add("")
if ($failures.Count -eq 0) {
    $lines.Add("No contract drift detected.")
} else {
    $lines.Add("| Status | Tag | Type | Output Path | Output Kind | Legacy Markers | Structure Issues |")
    $lines.Add("| --- | --- | --- | --- | --- | --- | --- |")
    foreach ($failure in ($failures | Sort-Object status, tag, packageName, typeName)) {
        $markers = ($failure.legacyMarkers -join ", ")
        $issues = ($failure.structureIssues -join ", ")
        $lines.Add("| $(Convert-MarkdownCell $failure.status) | $(Convert-MarkdownCell $failure.tag) | $(Convert-MarkdownCell $failure.typeName) | $(Convert-MarkdownCell $failure.outputPath) | $(Convert-MarkdownCell $failure.outputKind) | $(Convert-MarkdownCell $markers) | $(Convert-MarkdownCell $issues) |")
    }
}
$lines | Set-Content -LiteralPath $markdownReportFile -Encoding utf8NoBOM

if ($summary.failures -gt 0) {
    Write-Error "Design contract audit failed with $($summary.failures) drift item(s). See $markdownReportFile"
}

Write-Host "Design contract audit passed. See $markdownReportFile"
