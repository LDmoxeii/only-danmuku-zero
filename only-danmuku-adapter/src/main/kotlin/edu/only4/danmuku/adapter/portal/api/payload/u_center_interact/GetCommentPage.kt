package edu.only4.danmuku.adapter.portal.api.payload.u_center_interact

object GetCommentPage {

    data class Request(
        val videoId: Long?
    )

    data class Response(
        val commentId: String?,
        val avatar: String?,
        val videoId: String?,
        val videoName: String?,
        val videoCover: String,
        val content: String?,
        val imgPath: String?,
        val userId: String?,
        val nickName: String?,
        val replyNickName: String?,
        val postTime: Long
    )

}
