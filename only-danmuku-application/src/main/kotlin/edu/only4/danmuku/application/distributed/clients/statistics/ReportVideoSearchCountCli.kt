package edu.only4.danmuku.application.distributed.clients.statistics

import com.only4.cap4k.ddd.core.application.RequestParam

object ReportVideoSearchCountCli {

    data class Request(
        val keyword: String
    ) : RequestParam<Response>

    data object Response

}
