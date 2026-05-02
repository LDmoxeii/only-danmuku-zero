package edu.only4.danmuku.application.distributed.clients.authorize

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object IssueTokenCli {

    data class Request(
        val userId: UUID,
        val accountType: Int,
        val account: String,
        val extra: Map<String, Any> = emptyMap()
    ) : RequestParam<Response>

    data class Response(
        val token: String
    )

}

