package edu.only4.danmuku.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckFocusStatusQry {

    data class Request(
        val userId: Long,
        val focusUserId: Long
    ) : RequestParam<Response>

    data class Response(
        val haveFocus: Boolean
    )

}
