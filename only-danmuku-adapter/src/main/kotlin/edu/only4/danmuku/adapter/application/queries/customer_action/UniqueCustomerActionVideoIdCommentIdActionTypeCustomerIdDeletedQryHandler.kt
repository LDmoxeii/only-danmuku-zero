package edu.only4.danmuku.adapter.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.CustomerActionRepository
import edu.only4.danmuku.application.queries.customer_action.UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry
import edu.only4.danmuku.domain._share.meta.customer_action.SCustomerAction
import org.springframework.stereotype.Service

@Service
class UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQryHandler(
    private val repository: CustomerActionRepository,
) : Query<UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Request, UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Response> {
    override fun exec(request: UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Request): UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Response {
        val exists = repository.exists(
            SCustomerAction.specify { schema ->
                schema.all(
                    schema.videoId eq request.videoId,
                    schema.commentId eq request.commentId,
                    schema.actionType eq request.actionType,
                    schema.customerId eq request.customerId,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeCustomerActionId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Response(
            exists = exists
        )
    }
}
