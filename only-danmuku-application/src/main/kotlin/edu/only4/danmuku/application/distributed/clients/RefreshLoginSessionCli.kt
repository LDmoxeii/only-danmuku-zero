package edu.only4.danmuku.application.distributed.clients

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object RefreshLoginSessionCli {

    data class Request(
        val userId: UUID,
        val nickName: String?,
        val avatar: String?
    ) : RequestParam<Response>

    data object Response

}

