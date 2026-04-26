package edu.only4.danmuku.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoPostProcessingVariantParentIdQualityDeletedQry {

    data class Request(
        val parentId: Long,
        val quality: String,
        val deleted: Long,
        val excludeVideoPostProcessingVariantId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
