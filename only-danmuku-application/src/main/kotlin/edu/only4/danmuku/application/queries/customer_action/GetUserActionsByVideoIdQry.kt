
package edu.only4.danmuku.application.queries.customer_action

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType

object GetUserActionsByVideoIdQry {

    data class Request(
        val userId: UUID,
        val videoId: UUID
    ) : RequestParam<Response>

    data class Response(
        val items: List<ActionItem>
    ) {
        data class ActionItem(
            val actionId: UUID,
            val userId: UUID,
            val videoId: UUID,
            val videoName: String,
            val videoCover: String,
            val videoUserId: UUID,
            val commentId: UUID?,
            val actionType: ActionType,
            val actionCount: Int,
            val actionTime: Long
        )
    }

}

