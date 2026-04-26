package edu.only4.danmuku.adapter.portal.api.payload.video_danmuku

object PostDanmuku {

    data class Request(
        val videoId: Long,
        val fileId: Long,
        val text: String,
        val mode: Int,
        val color: String,
        val time: Int
    )

    class Response

}
