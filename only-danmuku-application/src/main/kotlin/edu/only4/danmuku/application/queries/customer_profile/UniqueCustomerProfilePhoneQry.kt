
package edu.only4.danmuku.application.queries.customer_profile

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueCustomerProfilePhoneQry {

    data class Request(
        val phone: String?,
        val excludeCustomerProfileId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

