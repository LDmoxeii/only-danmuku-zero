
package edu.only4.danmuku.application.queries.user

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueUserPhoneQry {

    data class Request(
        val phone: String?,
        val excludeUserId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

