
package edu.only4.danmuku.adapter.portal.api.payload.video_transcode

object AdminGetAbrPlaylist {

    data class Request(
        val filePostId: Long,
        val quality: String
    )

    data class Response(
        val playlistPath: String
    )

}
