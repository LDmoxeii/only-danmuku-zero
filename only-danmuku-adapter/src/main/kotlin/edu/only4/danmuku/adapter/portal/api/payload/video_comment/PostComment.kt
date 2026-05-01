
package edu.only4.danmuku.adapter.portal.api.payload.video_comment

object PostComment {

    data class Request(
        val videoId: Long,
        val replyCommentId: Long?,
        val content: String,
        val imgPath: String?
    )

    class Response

}
