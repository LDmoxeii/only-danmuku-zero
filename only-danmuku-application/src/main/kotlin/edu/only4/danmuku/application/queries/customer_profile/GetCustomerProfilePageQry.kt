
package edu.only4.danmuku.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCustomerProfilePageQry {

    data class Request(
        val nickNameFuzzy: String?,
        val status: Int?
    ) : RequestParam<Response>

    data object Response

}
