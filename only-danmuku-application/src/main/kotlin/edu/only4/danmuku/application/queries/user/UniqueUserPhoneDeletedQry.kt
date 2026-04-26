package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueUserPhoneDeletedQry {

    data class Request(
        val phone: String?,
        val deleted: Long,
        val excludeUserId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
