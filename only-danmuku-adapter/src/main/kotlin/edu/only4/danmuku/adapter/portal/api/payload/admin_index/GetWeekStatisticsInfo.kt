
package edu.only4.danmuku.adapter.portal.api.payload.admin_index

object GetWeekStatisticsInfo {

    data class Request(
        val dataType: Int = 0
    )

    data class Response(
        val statisticsDate: Long,
        val statisticsCount: Int
    )

}
