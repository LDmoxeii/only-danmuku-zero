
package edu.only4.danmuku.application.queries.customer_action

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType

object UniqueCustomerActionTypeQry {

    data class Request(
        val videoId: UUID,
        val commentId: UUID?,
        val actionType: ActionType,
        val customerId: UUID,
        val excludeCustomerActionId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

