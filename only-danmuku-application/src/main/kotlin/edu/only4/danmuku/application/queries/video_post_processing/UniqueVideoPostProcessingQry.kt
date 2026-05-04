
package edu.only4.danmuku.application.queries.video_post_processing

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoPostProcessingQry {

    data class Request(
        val videoPostId: UUID,
        val excludeVideoPostProcessingId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

