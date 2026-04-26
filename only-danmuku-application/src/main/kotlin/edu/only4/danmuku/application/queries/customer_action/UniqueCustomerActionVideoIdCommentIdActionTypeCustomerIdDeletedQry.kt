package edu.only4.danmuku.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry {

    data class Request(
        val videoId: Long,
        val commentId: Long?,
        val actionType: Int,
        val customerId: Long,
        val deleted: Long,
        val excludeCustomerActionId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
