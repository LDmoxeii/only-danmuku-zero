package edu.only4.danmuku.application.distributed.clients

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object RemoveVideoSearchIndexCli {

    data class Request(
        val videoId: UUID
    ) : RequestParam<Response>

    data object Response

}

