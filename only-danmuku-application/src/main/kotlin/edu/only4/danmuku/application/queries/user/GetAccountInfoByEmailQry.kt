package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.user.enums.UserType

object GetAccountInfoByEmailQry {

    data class Request(
        val email: String
    ) : RequestParam<Response>

    data class Response(
        val userId: Long,
        val nickName: String,
        val email: String,
        val password: String,
        val type: UserType
    )

}
