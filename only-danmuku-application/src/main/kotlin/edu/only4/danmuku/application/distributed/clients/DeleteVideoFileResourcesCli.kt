package edu.only4.danmuku.application.distributed.clients

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object DeleteVideoFileResourcesCli {

    data class Request(
        val videoId: UUID,
        val ownerId: UUID
    ) : RequestParam<Response>

    data object Response

}

