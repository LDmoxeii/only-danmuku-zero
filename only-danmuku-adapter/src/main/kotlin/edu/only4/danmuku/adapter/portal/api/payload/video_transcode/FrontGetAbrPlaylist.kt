package edu.only4.danmuku.adapter.portal.api.payload.video_transcode

object FrontGetAbrPlaylist {

    data class Request(
        val fileId: Long,
        val quality: String
    )

    data class Response(
        val playlistPath: String
    )

}
