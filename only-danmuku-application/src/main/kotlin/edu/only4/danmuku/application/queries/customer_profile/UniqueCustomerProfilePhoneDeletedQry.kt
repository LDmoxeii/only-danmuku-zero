package edu.only4.danmuku.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueCustomerProfilePhoneDeletedQry {

    data class Request(
        val phone: String?,
        val deleted: Long,
        val excludeCustomerProfileId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
