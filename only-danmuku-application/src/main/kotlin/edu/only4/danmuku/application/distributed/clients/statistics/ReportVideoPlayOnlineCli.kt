package edu.only4.danmuku.application.distributed.clients.statistics

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object ReportVideoPlayOnlineCli {

    data class Request(
        val fileId: UUID,
        val deviceId: String
    ) : RequestParam<Response>

    data class Response(
        val current: Long
    )

}

