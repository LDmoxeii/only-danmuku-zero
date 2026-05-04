
package edu.only4.danmuku.application.queries.customer_action

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType

object GetCollectionPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var customerId: UUID
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<ActionItem>
    ) {
        data class ActionItem(
            val actionId: UUID,
            val videoId: UUID?,
            val videoUserId: UUID,
            val commentId: UUID?,
            val actionType: ActionType,
            val actionCount: Int,
            val userId: UUID,
            val actionTime: Long,
            val videoName: String?,
            val videoCover: String?
        )
    }

}

