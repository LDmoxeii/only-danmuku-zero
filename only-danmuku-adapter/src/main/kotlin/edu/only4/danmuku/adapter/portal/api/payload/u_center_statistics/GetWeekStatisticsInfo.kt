package edu.only4.danmuku.adapter.portal.api.payload.u_center_statistics

object GetWeekStatisticsInfo {

    data class Request(
        val dataType: Int?
    )

    data class Response(
        val statisticsDate: Long,
        val statisticsCount: Int
    )

}
