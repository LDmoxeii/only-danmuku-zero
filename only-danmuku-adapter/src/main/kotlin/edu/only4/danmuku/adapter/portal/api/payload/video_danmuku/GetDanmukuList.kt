package edu.only4.danmuku.adapter.portal.api.payload.video_danmuku

object GetDanmukuList {

    data class Request(
        val fileId: Long,
        val videoId: Long
    )

    data class Response(
        val danmukuId: String?,
        val fileId: String?,
        val videoId: String?,
        val userId: String?,
        val text: String?,
        val mode: Int?,
        val color: String?,
        val time: Int?,
        val postTime: Long?,
        val videoName: String?,
        val videoCover: String?,
        val nickName: String?
    )

}
