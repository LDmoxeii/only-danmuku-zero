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
