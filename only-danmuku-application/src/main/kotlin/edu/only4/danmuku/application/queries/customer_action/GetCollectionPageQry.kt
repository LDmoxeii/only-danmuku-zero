package edu.only4.danmuku.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.PageQueryParam
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType

object GetCollectionPageQry {

    data class Request(
        val customerId: Long
    ) : PageQueryParam<Response>()

    data class Response(
        val actionId: Long,
        val videoId: Long?,
        val videoUserId: Long,
        val commentId: Long?,
        val actionType: ActionType,
        val actionCount: Int,
        val userId: Long,
        val actionTime: Long,
        val videoName: String?,
        val videoCover: String?
    )

}
