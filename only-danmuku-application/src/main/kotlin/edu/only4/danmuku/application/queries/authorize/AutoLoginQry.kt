
package edu.only4.danmuku.application.queries.authorize

import com.only4.cap4k.ddd.core.application.RequestParam

object AutoLoginQry {

    class Request : RequestParam<Response>

    data class Response(
        val userId: Long?,
        val nickName: String?,
        val avatar: String?,
        val expireAt: Long?,
        val token: String?
    )

}
