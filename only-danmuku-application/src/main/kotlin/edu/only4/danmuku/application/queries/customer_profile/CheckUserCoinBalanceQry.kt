
package edu.only4.danmuku.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckUserCoinBalanceQry {

    data class Request(
        val userId: Long,
        val requiredAmount: Int
    ) : RequestParam<Response>

    data class Response(
        val sufficient: Boolean,
        val currentBalance: Int
    )

}
