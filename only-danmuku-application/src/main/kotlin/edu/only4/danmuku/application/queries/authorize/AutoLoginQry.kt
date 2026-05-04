
package edu.only4.danmuku.application.queries.authorize

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object AutoLoginQry {

    class Request : RequestParam<Response>

    data class Response(
        val userId: UUID?,
        val nickName: String?,
        val avatar: String?,
        val expireAt: Long?,
        val token: String?
    )

}

