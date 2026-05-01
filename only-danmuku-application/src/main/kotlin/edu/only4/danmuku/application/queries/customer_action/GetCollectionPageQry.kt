
package edu.only4.danmuku.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType

object GetCollectionPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var customerId: Long
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<ActionItem>
    ) {
        data class ActionItem(
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

}
