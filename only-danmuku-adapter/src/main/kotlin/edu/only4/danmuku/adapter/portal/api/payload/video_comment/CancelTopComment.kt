package edu.only4.danmuku.adapter.portal.api.payload.video_comment

object CancelTopComment {

    data class Request(
        val commentId: Long
    )

    class Response

}
