
package edu.only4.danmuku.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckSeriesExistsQry {

    data class Request(
        val seriesId: Long,
        val userId: Long
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
