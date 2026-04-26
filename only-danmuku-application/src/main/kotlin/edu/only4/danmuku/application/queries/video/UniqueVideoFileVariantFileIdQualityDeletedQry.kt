package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFileVariantFileIdQualityDeletedQry {

    data class Request(
        val fileId: Long,
        val quality: String,
        val deleted: Long,
        val excludeVideoFileVariantId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
