$ErrorActionPreference = "Stop"

$projectRoot = Resolve-Path (Join-Path $PSScriptRoot "..\..")
$rawDesignFile = Join-Path $projectRoot "codegen\design\raw-drawing-board.json"
$activeIterateFile = Join-Path $projectRoot "iterate\drawing_board.json"
$outputFile = Join-Path $projectRoot "codegen\design\design.json"
$skippedFile = Join-Path $projectRoot "codegen\design\skipped-design.json"

$tagAliases = @{
    "cmd" = "command"
    "qry" = "query"
    "cli" = "client"
    "payload" = "api_payload"
    "de" = "domain_event"
}

$packageRootsByTag = @{
    "command" = "edu.only4.danmuku.application.commands"
    "query" = "edu.only4.danmuku.application.queries"
    "client" = "edu.only4.danmuku.application.distributed.clients"
    "api_payload" = "edu.only4.danmuku.adapter.portal.api.payload"
}

$reservedNestedTypeNames = @(
    "Any",
    "Array",
    "Boolean",
    "Byte",
    "Char",
    "Collection",
    "Double",
    "Float",
    "Int",
    "Iterable",
    "List",
    "Long",
    "Map",
    "MutableCollection",
    "MutableIterable",
    "MutableList",
    "MutableMap",
    "MutableSet",
    "Nothing",
    "Number",
    "Pair",
    "Sequence",
    "Set",
    "Short",
    "String",
    "Triple",
    "Unit",
    "Request",
    "Response"
)

$generatedTypeFqns = @{
    "UserType" = "edu.only4.danmuku.domain.aggregates.user.enums.UserType"
    "PostType" = "edu.only4.danmuku.domain.aggregates.video_post.enums.PostType"
    "EncryptMethod" = "edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod"
    "ActionType" = "edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType"
    "MessageType" = "edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType"
    "ReadType" = "edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType"
    "SexType" = "edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType"
    "ThemeType" = "edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType"
    "StatisticsDataType" = "edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType"
    "AbnormalOpType" = "edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType"
    "LoginResult" = "edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult"
    "LoginType" = "edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType"
    "RecommendType" = "edu.only4.danmuku.domain.aggregates.video.enums.RecommendType"
    "AuditStatus" = "edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus"
    "UploadStatus" = "edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus"
    "EncryptKeyStatus" = "edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus"
    "EncryptTokenStatus" = "edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus"
    "EncryptStatus" = "edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus"
    "TransferResult" = "edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult"
    "VideoStatus" = "edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus"
    "ProcessStatus" = "edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus"
    "QualityAuthPolicy" = "edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy"
    "Category" = "edu.only4.danmuku.domain.aggregates.category.Category"
    "CustomerAction" = "edu.only4.danmuku.domain.aggregates.customer_action.CustomerAction"
    "CustomerFocus" = "edu.only4.danmuku.domain.aggregates.customer_focus.CustomerFocus"
    "CustomerMessage" = "edu.only4.danmuku.domain.aggregates.customer_message.CustomerMessage"
    "CustomerProfile" = "edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile"
    "Statistics" = "edu.only4.danmuku.domain.aggregates.statistics.Statistics"
    "User" = "edu.only4.danmuku.domain.aggregates.user.User"
    "UserLoginLog" = "edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog"
    "Video" = "edu.only4.danmuku.domain.aggregates.video.Video"
    "VideoAuditTrace" = "edu.only4.danmuku.domain.aggregates.video_audit_trace.VideoAuditTrace"
    "VideoComment" = "edu.only4.danmuku.domain.aggregates.video_comment.VideoComment"
    "VideoDanmuku" = "edu.only4.danmuku.domain.aggregates.video_danmuku.VideoDanmuku"
    "VideoFilePost" = "edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost"
    "VideoFileUploadSession" = "edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession"
    "VideoHlsEncryptKey" = "edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.VideoHlsEncryptKey"
    "VideoHlsKeyToken" = "edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken"
    "VideoPlayHistory" = "edu.only4.danmuku.domain.aggregates.video_play_history.VideoPlayHistory"
    "VideoPost" = "edu.only4.danmuku.domain.aggregates.video_post.VideoPost"
    "VideoPostProcessing" = "edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing"
    "CaptchaChannel" = "com.only.engine.enums.CaptchaChannel"
}

function Normalize-Tag([string] $tag) {
    $normalized = $tag.Trim().ToLowerInvariant()
    if ($tagAliases.ContainsKey($normalized)) {
        return $tagAliases[$normalized]
    }
    return $normalized
}

function Normalize-Package($entry) {
    if ($null -eq $entry.package) {
        return
    }
    $tag = [string] $entry.tag
    if (-not $packageRootsByTag.ContainsKey($tag)) {
        return
    }
    $package = ([string] $entry.package).Trim(".")
    $root = $packageRootsByTag[$tag]
    if ($package -eq $root) {
        $entry.package = ""
        return
    }
    $prefix = "$root."
    if ($package.StartsWith($prefix, [System.StringComparison]::Ordinal)) {
        $entry.package = $package.Substring($prefix.Length)
    }
}

function Normalize-FieldType($field) {
    if ($null -eq $field -or $null -eq $field.type) {
        return
    }
    $type = [string] $field.type
    foreach ($entry in $generatedTypeFqns.GetEnumerator()) {
        $typeName = [regex]::Escape($entry.Key)
        $type = [regex]::Replace($type, "(?<![A-Za-z0-9_.])$typeName(?![A-Za-z0-9_])", $entry.Value)
    }
    $field.type = $type
}

function To-NestedTypeName([string] $rawName) {
    $parts = [regex]::Split($rawName, "[^A-Za-z0-9]+") | Where-Object { $_ -ne "" }
    return ($parts | ForEach-Object {
        if ($_.Length -eq 1) {
            $_.ToUpperInvariant()
        } else {
            $_.Substring(0, 1).ToUpperInvariant() + $_.Substring(1)
        }
    }) -join ""
}

function Normalize-NestedNamespace($entry, [string] $namespace, [string] $fieldProperty) {
    $fields = @($entry.$fieldProperty)
    if ($fields.Count -eq 0) {
        return $null
    }

    foreach ($field in $fields) {
        if ($null -ne $field.type -and ([string] $field.type) -match "(?<![A-Za-z0-9_.])Response(?![A-Za-z0-9_])") {
            return "unsupported self-recursive Response type in ${namespace}: $($field.name)"
        }
        if ($null -ne $field.type -and ([string] $field.type) -match "(?<![A-Za-z0-9_.])Item(?![A-Za-z0-9_])") {
            return "unsupported legacy Item response type in ${namespace}: $($field.name)"
        }
    }

    foreach ($field in $fields) {
        if ($null -ne $field.name) {
            $field.name = ([string] $field.name).Replace("[]", "")
        }
    }

    $groups = @{}
    foreach ($field in $fields) {
        $name = [string] $field.name
        if (-not $name.Contains(".")) {
            continue
        }
        $parts = $name.Split(".")
        if ($parts.Count -ne 2) {
            return "unsupported multi-level nested field in ${namespace}: $name"
        }
        $rootName = $parts[0]
        if (-not $groups.ContainsKey($rootName)) {
            $nestedTypeName = To-NestedTypeName $rootName
            if ($nestedTypeName -in $reservedNestedTypeNames) {
                return "unsupported reserved nested type name in ${namespace}: $nestedTypeName"
            }
            $groups[$rootName] = $nestedTypeName
        }
    }

    foreach ($rootName in $groups.Keys) {
        $directRoots = @($fields | Where-Object { $_.name -eq $rootName })
        if ($directRoots.Count -ne 1) {
            return "missing direct root field for nested field in ${namespace}: $rootName"
        }

        $nestedTypeName = $groups[$rootName]
        $directRoot = $directRoots[0]
        $currentType = [string] $directRoot.type
        if ($currentType -match "^(Collection|Iterable|List|MutableCollection|MutableList|MutableSet|Set)<.*>$") {
            $directRoot.type = "$($matches[1])<$nestedTypeName>"
        } else {
            $directRoot.type = $nestedTypeName
        }
    }

    return $null
}

function Normalize-DomainEventFields($entry) {
    if ($entry.tag -ne "domain_event") {
        return
    }
    if ($entry.PSObject.Properties.Name -notcontains "requestFields") {
        return
    }
    $entry.requestFields = @($entry.requestFields | Where-Object { ([string] $_.name) -ne "entity" })
}

function Normalize-Entry($entry) {
    $entry.tag = Normalize-Tag $entry.tag
    Normalize-Package $entry
    @($entry.requestFields) | ForEach-Object { Normalize-FieldType $_ }
    @($entry.responseFields) | ForEach-Object { Normalize-FieldType $_ }
    Normalize-DomainEventFields $entry
    $requestNestedError = Normalize-NestedNamespace $entry "request" "requestFields"
    if ($null -ne $requestNestedError) {
        return [pscustomobject]@{ Entry = $entry; SkipReason = $requestNestedError }
    }
    $responseNestedError = Normalize-NestedNamespace $entry "response" "responseFields"
    if ($null -ne $responseNestedError) {
        return [pscustomobject]@{ Entry = $entry; SkipReason = $responseNestedError }
    }
    return $entry
}

function Entry-Key($entry) {
    if ($entry.tag -eq "domain_event") {
        $aggregateKey = (@($entry.aggregates) | Select-Object -First 1)
        $eventNameKey = ([string] $entry.name) -replace "DomainEvent$", ""
        return "$($entry.tag)|$aggregateKey|$eventNameKey"
    }
    return "$($entry.tag)|$($entry.package)|$($entry.name)"
}

$rawEntries = @(Get-Content -Raw -LiteralPath $rawDesignFile | ConvertFrom-Json)
$activeEntries = @(Get-Content -Raw -LiteralPath $activeIterateFile | ConvertFrom-Json)

$merged = [System.Collections.Generic.List[object]]::new()
$skipped = [System.Collections.Generic.List[object]]::new()
$seen = [System.Collections.Generic.HashSet[string]]::new()

foreach ($entry in $rawEntries) {
    $normalized = Normalize-Entry $entry
    if ($normalized.PSObject.Properties.Name -contains "SkipReason") {
        $skipped.Add([pscustomobject]@{
            tag = $normalized.Entry.tag
            package = $normalized.Entry.package
            name = $normalized.Entry.name
            reason = $normalized.SkipReason
        })
        continue
    }
    if ($seen.Add((Entry-Key $normalized))) {
        $merged.Add($normalized)
    }
}

foreach ($entry in $activeEntries) {
    $normalized = Normalize-Entry $entry
    if ($normalized.PSObject.Properties.Name -contains "SkipReason") {
        $skipped.Add([pscustomobject]@{
            tag = $normalized.Entry.tag
            package = $normalized.Entry.package
            name = $normalized.Entry.name
            reason = $normalized.SkipReason
        })
        continue
    }
    if ($seen.Add((Entry-Key $normalized))) {
        $merged.Add($normalized)
    }
}

$json = $merged | ConvertTo-Json -Depth 100
[System.IO.File]::WriteAllText($outputFile, $json + [Environment]::NewLine, [System.Text.UTF8Encoding]::new($false))

$skippedJson = $skipped | ConvertTo-Json -Depth 20
[System.IO.File]::WriteAllText($skippedFile, $skippedJson + [Environment]::NewLine, [System.Text.UTF8Encoding]::new($false))

Write-Host "Wrote $($merged.Count) standardized design entries to $outputFile"
Write-Host "Skipped $($skipped.Count) unsupported design entries to $skippedFile"
