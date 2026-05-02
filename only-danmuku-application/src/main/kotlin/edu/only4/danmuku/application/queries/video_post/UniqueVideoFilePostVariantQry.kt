
package edu.only4.danmuku.application.queries.video_post

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostVariantQry {

    data class Request(
        val filePostId: UUID,
        val quality: String,
        val excludeVideoFilePostVariantId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

