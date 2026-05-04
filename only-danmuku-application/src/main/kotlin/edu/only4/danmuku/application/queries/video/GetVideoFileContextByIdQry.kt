
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoFileContextByIdQry {

    data class Request(
        val fileId: UUID
    ) : RequestParam<Response>

    data class Response(
        val videoId: UUID,
        val videoPostId: UUID,
        val fileIndex: Int
    )

}

