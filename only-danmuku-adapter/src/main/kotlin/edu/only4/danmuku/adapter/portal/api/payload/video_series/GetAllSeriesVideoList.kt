
package edu.only4.danmuku.adapter.portal.api.payload.video_series

object GetAllSeriesVideoList {

    data class Request(
        val seriesId: Long?
    )

    data class Response(
        val videoId: String?,
        val videoCover: String?,
        val videoName: String?,
        val playCount: Int?,
        val createTime: Long
    )

}
