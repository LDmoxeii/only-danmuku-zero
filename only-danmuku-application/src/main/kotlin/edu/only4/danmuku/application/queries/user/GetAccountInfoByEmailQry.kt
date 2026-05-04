
package edu.only4.danmuku.application.queries.user

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain._share.enums.UserType

object GetAccountInfoByEmailQry {

    data class Request(
        val email: String
    ) : RequestParam<Response>

    data class Response(
        val userId: UUID,
        val nickName: String,
        val email: String,
        val password: String,
        val type: UserType
    )

}

