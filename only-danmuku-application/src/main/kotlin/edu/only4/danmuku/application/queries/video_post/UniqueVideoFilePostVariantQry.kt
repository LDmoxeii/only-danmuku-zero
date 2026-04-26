package edu.only4.danmuku.application.queries.video_post

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostVariantQry {

    data class Request(
        val filePostId: Long,
        val quality: String,
        val excludeVideoFilePostVariantId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
