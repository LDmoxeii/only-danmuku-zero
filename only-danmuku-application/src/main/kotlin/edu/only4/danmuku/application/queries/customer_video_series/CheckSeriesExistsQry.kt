
package edu.only4.danmuku.application.queries.customer_video_series

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckSeriesExistsQry {

    data class Request(
        val seriesId: UUID,
        val userId: UUID
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

