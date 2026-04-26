package edu.only4.danmuku.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCustomerVideoSeriesVideoQry {

    data class Request(
        val userId: Long
    ) : RequestParam<Response>

    data class Response(
        val seriesId: Long,
        val seriesName: String?,
        val seriesDescription: String?,
        val sort: Int?,
        val videoList: List<VideoList>?
    ) {
        data class VideoList(
            val videoId: Long,
            val videoCover: String,
            val videoName: String,
            val playCount: Int,
            val sort: Int
        )
    }

}
