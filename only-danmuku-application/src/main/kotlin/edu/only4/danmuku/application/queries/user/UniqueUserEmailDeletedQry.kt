package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueUserEmailDeletedQry {

    data class Request(
        val email: String,
        val deleted: Long,
        val excludeUserId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
