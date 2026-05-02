
package edu.only4.danmuku.application.queries.customer_video_series

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCustomerVideoSeriesVideoQry {

    data class Request(
        val userId: UUID
    ) : RequestParam<Response>

    data class Response(
        val items: List<SeriesItem>
    ) {
        data class SeriesItem(
            val seriesId: UUID,
            val seriesName: String?,
            val seriesDescription: String?,
            val sort: Int?,
            val videoList: List<VideoItem>?
        )

        data class VideoItem(
            val videoId: UUID,
            val videoCover: String,
            val videoName: String,
            val playCount: Int,
            val sort: Int
        )
    }

}

