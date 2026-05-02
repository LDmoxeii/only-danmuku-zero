
package edu.only4.danmuku.application.queries.customer_profile

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueCustomerProfileNickNameQry {

    data class Request(
        val nickName: String,
        val excludeCustomerProfileId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

