package edu.only4.danmuku.domain.aggregates.customer_action

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_action.CustomerAction
import edu.only4.danmuku.domain.aggregates.customer_action.factory.CustomerActionFactory

/**
 * CustomerAction aggregate wrapper
 * 用户行为 点赞、评论
 */
class AggCustomerAction(
    payload: CustomerActionFactory.Payload? = null,
) : Aggregate.Default<CustomerAction>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCustomerAction, UUID>(key)
}

