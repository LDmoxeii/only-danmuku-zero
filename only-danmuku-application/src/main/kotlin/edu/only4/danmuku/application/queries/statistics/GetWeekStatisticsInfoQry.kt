
package edu.only4.danmuku.application.queries.statistics

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType

object GetWeekStatisticsInfoQry {

    data class Request(
        val userId: Long? = null,
        val dataType: StatisticsDataType = StatisticsDataType.UNKNOW
    ) : RequestParam<Response>

    data class Response(
        val items: List<StatisticsItem>
    ) {
        data class StatisticsItem(
            val date: Long,
            val count: Int
        )
    }

}
