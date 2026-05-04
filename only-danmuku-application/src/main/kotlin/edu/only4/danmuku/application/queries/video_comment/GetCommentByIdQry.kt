
package edu.only4.danmuku.application.queries.video_comment

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCommentByIdQry {

    data class Request(
        val commentId: UUID
    ) : RequestParam<Response>

    data class Response(
        val commentId: UUID,
        val videoId: UUID,
        val videoOwnerId: UUID,
        val userId: UUID,
        val parentId: UUID,
        val content: String?
    )

}

