
package edu.only4.danmuku.application.queries.customer_video_series

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckSeriesNameExistsQry {

    data class Request(
        val customerId: UUID,
        val seriesName: String,
        val excludeSeriesId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

