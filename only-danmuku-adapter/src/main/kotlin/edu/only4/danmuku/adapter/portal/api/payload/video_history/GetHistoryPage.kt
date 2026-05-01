
package edu.only4.danmuku.adapter.portal.api.payload.video_history

object GetHistoryPage {

    class Request

    data class Response(
        val historyId: String?,
        val videoId: String?,
        val videoName: String?,
        val videoCover: String?,
        val fileIndex: Int?,
        val playTime: Long?
    )

}
