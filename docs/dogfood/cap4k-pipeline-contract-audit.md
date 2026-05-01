# Cap4k Pipeline Contract Audit

Design input: `codegen\design\design.json`

Plan input: `build\cap4k\plan.json`

The audit reads planned `outputPath` values from `cap4kPlan`; it does not require contracts to be generated under `build/generated`.

## Summary

| Metric | Count |
| --- | ---: |
| Total Query/Cmd/Cli contracts | 206 |
| Planned and matched contracts | 174 |
| Failures | 32 |
| PLANNED_AND_MATCHED | 174 |
| CONTRACT_STRUCTURE_DRIFT | 32 |

## Failures

| Status | Tag | Type | Output Path | Output Kind | Legacy Markers | Structure Issues |
| --- | --- | --- | --- | --- | --- | --- |
| CONTRACT_STRUCTURE_DRIFT | command | FinalizeCategoryAfterCreateCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\category\FinalizeCategoryAfterCreateCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendCollectMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendCollectMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendCommentMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendCommentMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendLikeMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendLikeMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendReplyMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendReplyMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendVideoAuditFailedMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendVideoAuditFailedMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | SendVideoAuditPassedMessageCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_message\SendVideoAuditPassedMessageCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | BindPhoneCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_profile\BindPhoneCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | CreateCustomerProfileCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_profile\CreateCustomerProfileCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | TransferCoinCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_profile\TransferCoinCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | DeleteVideoSeriesCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_video_series\DeleteVideoSeriesCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | UpdateCustomerVideoSeriesVideosCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_video_series\UpdateCustomerVideoSeriesVideosCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | UpdateVideoSeriesSortCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\customer_video_series\UpdateVideoSeriesSortCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ChangePasswordCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\user\ChangePasswordCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ChangeUserPhoneCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\user\ChangeUserPhoneCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ApplyCustomerDislikedCommentCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_comment\ApplyCustomerDislikedCommentCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ApplyCustomerLikedCommentCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_comment\ApplyCustomerLikedCommentCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ApplyCustomerUndislikedCommentCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_comment\ApplyCustomerUndislikedCommentCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | ApplyCustomerUnlikedCommentCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_comment\ApplyCustomerUnlikedCommentCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | BatchDeleteCommentCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_comment\BatchDeleteCommentCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | BatchDeleteDanmukuCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_danmuku\BatchDeleteDanmukuCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | InitTempAndStartUploadingCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_file_upload_session\InitTempAndStartUploadingCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | command | StartVideoPostProcessingCmd | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\commands\video_post_processing\StartVideoPostProcessingCmd.kt | CHECKED_IN_SOURCE |  | missing Response type |
| CONTRACT_STRUCTURE_DRIFT | query | GetCollectionPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\customer_action\GetCollectionPageQry.kt | CHECKED_IN_SOURCE |  | missing request field customerId |
| CONTRACT_STRUCTURE_DRIFT | query | GetFocusPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\customer_focus\GetFocusPageQry.kt | CHECKED_IN_SOURCE |  | missing request field userId |
| CONTRACT_STRUCTURE_DRIFT | query | GetCustomerProfilePageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\customer_profile\GetCustomerProfilePageQry.kt | CHECKED_IN_SOURCE |  | missing request field nickNameFuzzy, missing request field status |
| CONTRACT_STRUCTURE_DRIFT | query | GetHotVideoPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video\GetHotVideoPageQry.kt | CHECKED_IN_SOURCE |  | missing request field lastPlayHour |
| CONTRACT_STRUCTURE_DRIFT | query | GetVideoPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video\GetVideoPageQry.kt | CHECKED_IN_SOURCE |  | missing request field categoryParentId, missing request field videoNameFuzzy, missing request field excludeVideoIds |
| CONTRACT_STRUCTURE_DRIFT | query | VideoCommentPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video_comment\VideoCommentPageQry.kt | CHECKED_IN_SOURCE |  | missing request field videoNameFuzzy |
| CONTRACT_STRUCTURE_DRIFT | query | GetVideoDanmukuPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video_danmuku\GetVideoDanmukuPageQry.kt | CHECKED_IN_SOURCE |  | missing request field videoUserId, missing request field videoNameFuzzy |
| CONTRACT_STRUCTURE_DRIFT | query | GetUserVideoPostQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video_draft\GetUserVideoPostQry.kt | CHECKED_IN_SOURCE |  | missing request field userId, missing request field videoNameFuzzy, missing request field excludeStatusArray |
| CONTRACT_STRUCTURE_DRIFT | query | GetVideoPostPageQry | only-danmuku-application\src\main\kotlin\edu\only4\danmuku\application\queries\video_draft\GetVideoPostPageQry.kt | CHECKED_IN_SOURCE |  | missing request field categoryParentId, missing request field videoNameFuzzy, missing request field excludeVideoIds |
