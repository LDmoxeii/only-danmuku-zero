
package edu.only4.danmuku.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckSeriesNameExistsQry {

    data class Request(
        val customerId: Long,
        val seriesName: String,
        val excludeSeriesId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
