package edu.only4.danmuku.adapter.portal.api.payload.video_history

object DeleteVideoHistory {

    data class Request(
        val videoId: Long
    )

    class Response

}
