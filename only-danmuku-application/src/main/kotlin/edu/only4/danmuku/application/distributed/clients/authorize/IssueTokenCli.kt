package edu.only4.danmuku.application.distributed.clients.authorize

import com.only4.cap4k.ddd.core.application.RequestParam

object IssueTokenCli {

    data class Request(
        val userId: Long,
        val accountType: Int,
        val account: String,
        val extra: Map<String, Any>
    ) : RequestParam<Response>

    data class Response(
        val token: String
    )

}
