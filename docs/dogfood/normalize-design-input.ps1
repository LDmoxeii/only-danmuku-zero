$ErrorActionPreference = "Stop"

if ($PSVersionTable.PSVersion.Major -lt 7) {
    throw "normalize-design-input.ps1 requires PowerShell 7+ (pwsh) for stable UTF-8 JSON formatting."
}

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

$generatedTypeFqns = @{
    "UserType" = "edu.only4.danmuku.domain._share.enums.UserType"
    "PostType" = "edu.only4.danmuku.domain._share.enums.PostType"
    "EncryptMethod" = "edu.only4.danmuku.domain._share.enums.EncryptMethod"
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

function Test-TypeContainsToken([string] $type, [string] $token) {
    return $type -match "(?<![A-Za-z0-9_.])$([regex]::Escape($token))(?![A-Za-z0-9_])"
}

function Test-TypeSimpleName([string] $type, [string] $simpleName) {
    return $type -match "(^|[.<, ])$([regex]::Escape($simpleName))($|[<>, ])"
}

function Replace-TypeToken([string] $type, [string] $token, [string] $replacement) {
    return [regex]::Replace($type, "(?<![A-Za-z0-9_.])$([regex]::Escape($token))(?![A-Za-z0-9_])", $replacement)
}

function Get-NestedChildFields($fields, [string] $fieldName) {
    $prefix = "$fieldName."
    $listPrefix = "$fieldName[]."
    return @($fields | Where-Object {
        $null -ne $_.name -and (
            ([string] $_.name).StartsWith($prefix, [System.StringComparison]::Ordinal) -or
            ([string] $_.name).StartsWith($listPrefix, [System.StringComparison]::Ordinal)
        )
    })
}

function Copy-FieldWithName($field, [string] $name) {
    $copy = [ordered]@{}
    foreach ($property in $field.PSObject.Properties) {
        $copy[$property.Name] = $property.Value
    }
    $copy["name"] = $name
    return [pscustomobject]$copy
}

function New-Field([string] $name, [string] $type, [bool] $nullable, $defaultValue = $null) {
    $field = [ordered]@{
        name = $name
        type = $type
        nullable = $nullable
    }
    if ($null -ne $defaultValue) {
        $field["defaultValue"] = $defaultValue
    }
    return [pscustomobject]$field
}

function Set-EntryProperty($entry, [string] $name, $value) {
    if ($entry.PSObject.Properties.Name -contains $name) {
        $entry.$name = $value
    } else {
        $entry | Add-Member -NotePropertyName $name -NotePropertyValue $value
    }
}

function Expand-RootRecursiveTreeFields($fields, [string] $namespace) {
    if ($namespace -ne "response") {
        return @($fields)
    }

    $expanded = [System.Collections.Generic.List[object]]::new()
    foreach ($field in $fields) {
        $expanded.Add($field)
    }

    foreach ($field in $fields) {
        $fieldName = [string] $field.name
        if ($fieldName -ne "children") {
            continue
        }
        if (-not (Test-TypeContainsToken ([string] $field.type) "Children")) {
            continue
        }
        if ((Get-NestedChildFields $fields $fieldName).Count -gt 0) {
            continue
        }

        $rootScalarFields = @($fields | Where-Object {
            $null -ne $_.name -and
            -not ([string] $_.name).Contains(".") -and
            ([string] $_.name) -ne "children"
        })

        foreach ($rootField in $rootScalarFields) {
            $expanded.Add((Copy-FieldWithName $rootField "children.$([string] $rootField.name)"))
        }
        $expanded.Add((Copy-FieldWithName $field "children.children"))
    }

    return @($expanded)
}

function Normalize-RecursiveFieldTypes($fields, [string] $namespace) {
    foreach ($field in $fields) {
        if ($null -eq $field.type) {
            continue
        }

        $fieldName = [string] $field.name
        $fieldType = [string] $field.type
        $isRootField = -not $fieldName.Contains(".")
        $isKnownRootTreeField = $namespace -eq "response" -and $isRootField -and $fieldName -eq "children"
        $childFields = Get-NestedChildFields $fields $fieldName

        if (Test-TypeContainsToken $fieldType "self") {
            if ($isKnownRootTreeField) {
                $fieldType = Replace-TypeToken $fieldType "self" "Children"
                $field.type = $fieldType
            } else {
                return "unsupported self recursion type in ${namespace}: $fieldName"
            }
        }

        if (Test-TypeContainsToken $fieldType "Response") {
            if ($isKnownRootTreeField) {
                $fieldType = Replace-TypeToken $fieldType "Response" "Children"
                $field.type = $fieldType
            } else {
                return "unsupported Response recursion type in ${namespace}: $fieldName"
            }
        }

        if (Test-TypeContainsToken $fieldType "Item") {
            if ($isKnownRootTreeField -and $childFields.Count -eq 0) {
                $fieldType = Replace-TypeToken $fieldType "Item" "Children"
                $field.type = $fieldType
            } elseif ($childFields.Count -gt 0) {
                # A direct field with child declarations defines a local Item model.
                $field.type = $fieldType
            } else {
                return "unsupported Item type without local child fields in ${namespace}: $fieldName"
            }
        }
    }

    return $null
}

function Normalize-NestedNamespace($entry, [string] $namespace, [string] $fieldProperty) {
    if ($entry.PSObject.Properties.Name -notcontains $fieldProperty) {
        return $null
    }

    $fields = @($entry.$fieldProperty | Where-Object { $null -ne $_ })
    if ($fields.Count -eq 0) {
        $entry.$fieldProperty = @()
        return $null
    }

    $recursiveTypeError = Normalize-RecursiveFieldTypes $fields $namespace
    if ($null -ne $recursiveTypeError) {
        return $recursiveTypeError
    }

    $fields = Expand-RootRecursiveTreeFields $fields $namespace
    $entry.$fieldProperty = @($fields)

    foreach ($field in $fields) {
        $name = [string] $field.name
        if (-not $name.Contains(".")) {
            continue
        }

        $parts = $name.Split(".")
        for ($index = 1; $index -lt $parts.Count; $index++) {
            $containerPrefix = ($parts[0..($index - 1)] -join ".")
            if ($containerPrefix.EndsWith(".list[]", [System.StringComparison]::Ordinal)) {
                $pagePrefix = $containerPrefix.Substring(0, $containerPrefix.Length - ".list[]".Length)
                $pageContainers = @($fields | Where-Object {
                    $_.name -eq $pagePrefix -and
                    (Test-TypeSimpleName ([string] $_.type) "PageData")
                })
                if ($pageContainers.Count -eq 1) {
                    continue
                }
            }

            $containerCandidates = @($containerPrefix)
            if ($containerPrefix.Contains("[]")) {
                $containerCandidates += ($containerPrefix -replace "\[\]$", "")
                $containerCandidates += $containerPrefix.Replace("[]", "")
            }

            $directContainers = @($fields | Where-Object { $containerCandidates -contains $_.name })
            if ($directContainers.Count -eq 0) {
                return "missing direct container field for nested field in ${namespace}: $containerPrefix (required by $name)"
            }
            if ($directContainers.Count -gt 1) {
                return "duplicate direct container field for nested field in ${namespace}: $containerPrefix (required by $name)"
            }
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

function Apply-KnownContractOverrides($entry) {
    if ($entry.tag -ne "query") {
        return
    }

    if ($entry.package -eq "category" -and $entry.name -eq "GetCategoryTree") {
        Set-EntryProperty $entry "responseFields" @(
            New-Field "items" "List<CategoryItem>" $false
            New-Field "items[].categoryId" "Long" $false
            New-Field "items[].code" "String" $false
            New-Field "items[].name" "String" $false
            New-Field "items[].parentId" "Long" $false
            New-Field "items[].icon" "String" $true
            New-Field "items[].background" "String" $true
            New-Field "items[].sort" "Int" $false
            New-Field "items[].children" "List<Children>" $false
            New-Field "items[].children.categoryId" "Long" $false
            New-Field "items[].children.code" "String" $false
            New-Field "items[].children.name" "String" $false
            New-Field "items[].children.parentId" "Long" $false
            New-Field "items[].children.icon" "String" $true
            New-Field "items[].children.background" "String" $true
            New-Field "items[].children.sort" "Int" $false
            New-Field "items[].children.children" "List<Children>" $false
        )
        return
    }

    if ($entry.package -eq "video" -and $entry.name -eq "GetVideoAllList") {
        Set-EntryProperty $entry "responseFields" @(
            New-Field "items" "List<VideoItem>" $false
            New-Field "items[].videoId" "Long" $false
            New-Field "items[].videoCover" "String" $true
            New-Field "items[].videoName" "String" $true
            New-Field "items[].userId" "Long" $true
            New-Field "items[].createTime" "Long" $false
            New-Field "items[].lastUpdateTime" "Long" $true
            New-Field "items[].parentCategoryId" "Long" $false
            New-Field "items[].categoryId" "Long" $true
            New-Field "items[].postType" "PostType" $false
            New-Field "items[].originInfo" "String" $true
            New-Field "items[].tags" "String" $true
            New-Field "items[].introduction" "String" $true
            New-Field "items[].duration" "Int" $false
            New-Field "items[].status" "VideoStatus" $false
            New-Field "items[].playCount" "Int" $false
            New-Field "items[].likeCount" "Int" $false
            New-Field "items[].danmukuCount" "Int" $false
            New-Field "items[].commentCount" "Int" $false
            New-Field "items[].coinCount" "Int" $false
            New-Field "items[].collectCount" "Int" $false
            New-Field "items[].recommendType" "RecommendType" $false
            New-Field "items[].lastPlayTime" "Long" $true
            New-Field "items[].nickName" "String" $true "null"
            New-Field "items[].avatar" "String" $true "null"
            New-Field "items[].categoryFullName" "String" $true
        )
        return
    }

    if ($entry.package -eq "video_comment" -and $entry.name -eq "VideoCommentPage") {
        Set-EntryProperty $entry "traits" @("page")
        Set-EntryProperty $entry "responseFields" @(
            New-Field "page" "com.only4.cap4k.ddd.core.share.PageData<CommentItem>" $false
            New-Field "page.list[].commentId" "Long" $false
            New-Field "page.list[].parentCommentId" "Long" $false
            New-Field "page.list[].videoId" "Long" $false
            New-Field "page.list[].videoUserId" "Long" $false
            New-Field "page.list[].videoName" "String" $false
            New-Field "page.list[].videoCover" "String" $false
            New-Field "page.list[].content" "String" $true
            New-Field "page.list[].imgPath" "String" $true
            New-Field "page.list[].customerId" "Long" $false
            New-Field "page.list[].customerNickname" "String" $false
            New-Field "page.list[].customerAvatar" "String" $true
            New-Field "page.list[].replyCustomerId" "Long" $true
            New-Field "page.list[].replyCustomerNickname" "String" $true
            New-Field "page.list[].postTime" "Long" $false
            New-Field "page.list[].likeCount" "Int" $true "0"
            New-Field "page.list[].hateCount" "Int" $true "0"
            New-Field "page.list[].topType" "Int" $true "0"
            New-Field "page.list[].childrenCount" "Int" $false "0"
            New-Field "page.list[].children" "List<Children>" $true
            New-Field "page.list[].children.commentId" "Long" $false
            New-Field "page.list[].children.parentCommentId" "Long" $false
            New-Field "page.list[].children.videoId" "Long" $false
            New-Field "page.list[].children.videoUserId" "Long" $false
            New-Field "page.list[].children.videoName" "String" $false
            New-Field "page.list[].children.videoCover" "String" $false
            New-Field "page.list[].children.content" "String" $true
            New-Field "page.list[].children.imgPath" "String" $true
            New-Field "page.list[].children.customerId" "Long" $false
            New-Field "page.list[].children.customerNickname" "String" $false
            New-Field "page.list[].children.customerAvatar" "String" $true
            New-Field "page.list[].children.replyCustomerId" "Long" $true
            New-Field "page.list[].children.replyCustomerNickname" "String" $true
            New-Field "page.list[].children.postTime" "Long" $false
            New-Field "page.list[].children.likeCount" "Int" $true "0"
            New-Field "page.list[].children.hateCount" "Int" $true "0"
            New-Field "page.list[].children.topType" "Int" $true "0"
            New-Field "page.list[].children.childrenCount" "Int" $false "0"
            New-Field "page.list[].children.children" "List<Children>" $true
        )
    }
}

function Normalize-Entry($entry) {
    $entry.tag = Normalize-Tag $entry.tag
    Normalize-Package $entry
    Apply-KnownContractOverrides $entry
    if ($entry.PSObject.Properties.Name -contains "requestFields") {
        @($entry.requestFields) | ForEach-Object { Normalize-FieldType $_ }
    }
    if ($entry.PSObject.Properties.Name -contains "responseFields") {
        @($entry.responseFields) | ForEach-Object { Normalize-FieldType $_ }
    }
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

function Convert-JsonLiteral([string] $value) {
    return ConvertTo-Json -InputObject $value -Compress
}

function Convert-JsonValue($value) {
    if ($null -eq $value) {
        return "null"
    }
    return ConvertTo-Json -InputObject $value -Depth 100 -Compress
}

function Convert-CompactObjectJson($value) {
    $parts = [System.Collections.Generic.List[string]]::new()
    foreach ($property in $value.PSObject.Properties) {
        $parts.Add("$(Convert-JsonLiteral $property.Name): $(Convert-JsonValue $property.Value)")
    }
    return "{$($parts -join ', ')}"
}

function Add-FieldArrayJsonLines(
    [System.Collections.Generic.List[string]] $lines,
    [string] $propertyName,
    $fields,
    [bool] $isLastProperty
) {
    $propertySuffix = if ($isLastProperty) { "" } else { "," }
    $fieldItems = @($fields | Where-Object { $null -ne $_ })

    if ($fieldItems.Count -eq 0) {
        $lines.Add("    $(Convert-JsonLiteral $propertyName): []$propertySuffix")
        return
    }

    $lines.Add("    $(Convert-JsonLiteral $propertyName): [")
    for ($index = 0; $index -lt $fieldItems.Count; $index++) {
        $itemSuffix = if ($index -eq $fieldItems.Count - 1) { "" } else { "," }
        $lines.Add("      $(Convert-CompactObjectJson $fieldItems[$index])$itemSuffix")
    }
    $lines.Add("    ]$propertySuffix")
}

function Convert-DesignEntriesJson($entries) {
    $entryItems = @($entries)
    $lines = [System.Collections.Generic.List[string]]::new()
    $lines.Add("[")

    for ($entryIndex = 0; $entryIndex -lt $entryItems.Count; $entryIndex++) {
        $entry = $entryItems[$entryIndex]
        $entrySuffix = if ($entryIndex -eq $entryItems.Count - 1) { "" } else { "," }
        $properties = @($entry.PSObject.Properties)

        $lines.Add("  {")
        for ($propertyIndex = 0; $propertyIndex -lt $properties.Count; $propertyIndex++) {
            $property = $properties[$propertyIndex]
            $isLastProperty = $propertyIndex -eq $properties.Count - 1
            if ($property.Name -eq "requestFields" -or $property.Name -eq "responseFields") {
                Add-FieldArrayJsonLines $lines $property.Name $property.Value $isLastProperty
                continue
            }

            $propertySuffix = if ($isLastProperty) { "" } else { "," }
            $lines.Add("    $(Convert-JsonLiteral $property.Name): $(Convert-JsonValue $property.Value)$propertySuffix")
        }
        $lines.Add("  }$entrySuffix")
    }

    $lines.Add("]")
    return ($lines -join [Environment]::NewLine)
}

$rawEntries = [object[]](Get-Content -Raw -Encoding utf8 -LiteralPath $rawDesignFile | ConvertFrom-Json)
$activeEntries = [object[]](Get-Content -Raw -Encoding utf8 -LiteralPath $activeIterateFile | ConvertFrom-Json)

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

$json = Convert-DesignEntriesJson $merged
[System.IO.File]::WriteAllText($outputFile, $json + [Environment]::NewLine, [System.Text.UTF8Encoding]::new($false))

$skippedJson = if ($skipped.Count -eq 0) { "[]" } else { ConvertTo-Json -InputObject @($skipped) -Depth 20 }
[System.IO.File]::WriteAllText($skippedFile, $skippedJson + [Environment]::NewLine, [System.Text.UTF8Encoding]::new($false))

Write-Host "Wrote $($merged.Count) standardized design entries to $outputFile"
Write-Host "Skipped $($skipped.Count) unsupported design entries to $skippedFile"
