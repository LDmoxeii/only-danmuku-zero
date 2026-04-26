package edu.only4.danmuku.adapter.portal.api.payload.u_home

object GetVideoPage {

    data class Request(
        val userId: Long,
        val type: Int?,
        val videoName: String?,
        val orderType: Int?
    )

    data class Response(
        val videoId: String?,
        val videoCover: String?,
        val videoName: String?,
        val createTime: Long,
        val playCount: Int?,
        val likeCount: Int?,
        val danmukuCount: Int?,
        val commentCount: Int?
    )

}
