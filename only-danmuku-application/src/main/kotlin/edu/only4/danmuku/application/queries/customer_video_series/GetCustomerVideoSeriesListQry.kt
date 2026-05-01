
package edu.only4.danmuku.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCustomerVideoSeriesListQry {

    data class Request(
        val userId: Long
    ) : RequestParam<Response>

    data class Response(
        val items: List<SeriesItem>
    ) {
        data class SeriesItem(
            val seriesId: Long,
            val seriesName: String?,
            val seriesDescription: String?,
            val sort: Int?,
            val videoCount: Int? = 0,
            val cover: String?,
            val createTime: Long,
            val updateTime: Long?
        )
    }

}
