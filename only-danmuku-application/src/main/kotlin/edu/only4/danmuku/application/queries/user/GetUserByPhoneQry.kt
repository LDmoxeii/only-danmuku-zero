
package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain._share.enums.UserType

object GetUserByPhoneQry {

    data class Request(
        val phone: String
    ) : RequestParam<Response>

    data class Response(
        val userId: Long,
        val nickName: String,
        val type: UserType
    )

}
