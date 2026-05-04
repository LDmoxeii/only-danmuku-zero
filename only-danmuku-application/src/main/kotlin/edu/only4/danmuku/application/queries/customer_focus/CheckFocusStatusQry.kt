
package edu.only4.danmuku.application.queries.customer_focus

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckFocusStatusQry {

    data class Request(
        val userId: UUID,
        val focusUserId: UUID
    ) : RequestParam<Response>

    data class Response(
        val haveFocus: Boolean
    )

}

