
package edu.only4.danmuku.application.queries.video_post

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostQry {

    data class Request(
        val videoPostId: UUID,
        val fileIndex: Int,
        val excludeVideoFilePostId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

