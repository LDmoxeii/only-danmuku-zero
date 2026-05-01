
package edu.only4.danmuku.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCommentByIdQry {

    data class Request(
        val commentId: Long
    ) : RequestParam<Response>

    data class Response(
        val commentId: Long,
        val videoId: Long,
        val videoOwnerId: Long,
        val userId: Long,
        val parentId: Long,
        val content: String?
    )

}
