
package edu.only4.danmuku.application.queries.customer_video_series

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCustomerVideoSeriesInfoQry {

    data class Request(
        val seriesId: UUID
    ) : RequestParam<Response>

    data class Response(
        val seriesId: UUID,
        val userId: UUID,
        val seriesName: String?,
        val seriesDescription: String?,
        val sort: Int?,
        val createTime: Long,
        val updateTime: Long?,
        val videoList: List<VideoItem>?
    ) {
        data class VideoItem(
            val videoId: UUID,
            val videoCover: String,
            val videoName: String,
            val playCount: Int,
            val sort: Int,
            val createTime: Long
        )
    }

}

