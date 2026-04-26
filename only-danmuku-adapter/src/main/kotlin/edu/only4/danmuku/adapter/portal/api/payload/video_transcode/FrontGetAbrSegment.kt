package edu.only4.danmuku.adapter.portal.api.payload.video_transcode

object FrontGetAbrSegment {

    data class Request(
        val fileId: Long,
        val quality: String,
        val ts: String
    )

    class Response

}
