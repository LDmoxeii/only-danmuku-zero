# Cap4k Pipeline Contract Drift Closure

Source report: `docs/dogfood/cap4k-pipeline-contract-audit.md`

Current baseline:

- Total Query/Cmd/Cli contracts: 206
- Planned and matched contracts: 206
- Open drift items: 0
- Closed drift class: `CONTRACT_STRUCTURE_DRIFT`

The previous 9 query request-field findings were audit false positives caused by `var` request properties. The audit now accepts both `val` and `var` contract fields.

## Verified Fix Rule

The closed items were command empty-response shape drift. The planned/generated contract expects an explicit generated empty response shape, while the migrated source still declared a plain `class Response`.

Verified fix:

- The 23 command files were backed up under `build/dogfood/contract-drift-sandbox/originals`.
- The 23 matching command design entries were isolated under `build/dogfood/contract-drift-sandbox/design-only-open-drift.json`.
- `cap4kGenerate` was run with only `designCommand` enabled and `OVERWRITE` conflict policy; generated output produced 23 `data object Response` declarations and 0 `class Response` declarations.
- Hand-written handler logic was restored from the backup and the empty-response contract was aligned to the generated shape.
- Final verification passed with `pwsh -NoProfile -ExecutionPolicy Bypass -File .\docs\dogfood\audit-design-contracts.ps1`.
- Final verification passed with `.\gradlew.bat --no-configuration-cache --no-build-cache compileKotlin`.

## Closed Items

| Area | Type | File | Drift | Fix |
| --- | --- | --- | --- | --- |
| category | `FinalizeCategoryAfterCreateCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/category/FinalizeCategoryAfterCreateCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendCollectMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendCollectMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendCommentMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendCommentMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendLikeMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendLikeMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendReplyMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendReplyMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendVideoAuditFailedMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendVideoAuditFailedMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_message | `SendVideoAuditPassedMessageCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_message/SendVideoAuditPassedMessageCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_profile | `BindPhoneCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_profile/BindPhoneCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_profile | `CreateCustomerProfileCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_profile/CreateCustomerProfileCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_profile | `TransferCoinCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_profile/TransferCoinCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_video_series | `DeleteVideoSeriesCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_video_series/DeleteVideoSeriesCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_video_series | `UpdateCustomerVideoSeriesVideosCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_video_series/UpdateCustomerVideoSeriesVideosCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| customer_video_series | `UpdateVideoSeriesSortCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_video_series/UpdateVideoSeriesSortCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| user | `ChangePasswordCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/user/ChangePasswordCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| user | `ChangeUserPhoneCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/user/ChangeUserPhoneCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_comment | `ApplyCustomerDislikedCommentCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_comment/ApplyCustomerDislikedCommentCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_comment | `ApplyCustomerLikedCommentCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_comment/ApplyCustomerLikedCommentCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_comment | `ApplyCustomerUndislikedCommentCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_comment/ApplyCustomerUndislikedCommentCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_comment | `ApplyCustomerUnlikedCommentCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_comment/ApplyCustomerUnlikedCommentCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_comment | `BatchDeleteCommentCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_comment/BatchDeleteCommentCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_danmuku | `BatchDeleteDanmukuCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_danmuku/BatchDeleteDanmukuCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_file_upload_session | `InitTempAndStartUploadingCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_file_upload_session/InitTempAndStartUploadingCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
| video_post_processing | `StartVideoPostProcessingCmd` | `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/video_post_processing/StartVideoPostProcessingCmd.kt` | `class Response` | Convert empty response to `data object Response`; update handler returns. |
