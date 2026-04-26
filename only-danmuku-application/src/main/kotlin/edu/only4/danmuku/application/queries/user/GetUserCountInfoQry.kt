package edu.only4.danmuku.application.queries.user

import com.only4.cap4k.ddd.core.application.RequestParam

object GetUserCountInfoQry {

    data class Request(
        val customerId: Long
    ) : RequestParam<Response>

    data class Response(
        val fansCount: Long,
        val currentCoinCount: Int,
        val focusCount: Long
    )

}
