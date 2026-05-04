
package edu.only4.danmuku.application.queries.statistics

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetPreviousDayStatisticsInfoQry {

    data class Request(
        val userId: UUID? = null
    ) : RequestParam<Response>

    data class Response(
        val userCount: Int = 0,
        val playCount: Int = 0,
        val commentCount: Int = 0,
        val danmukuCount: Int = 0,
        val likeCount: Int = 0,
        val collectCount: Int = 0,
        val coinCount: Int = 0
    )

}

