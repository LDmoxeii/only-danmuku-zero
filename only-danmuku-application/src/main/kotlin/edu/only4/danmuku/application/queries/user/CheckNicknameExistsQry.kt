package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckNicknameExistsQry {

    data class Request(
        val nickName: String,
        val excludeUserId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
