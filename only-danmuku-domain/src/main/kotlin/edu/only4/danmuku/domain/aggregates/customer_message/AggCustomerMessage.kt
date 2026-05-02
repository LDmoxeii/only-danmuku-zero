package edu.only4.danmuku.domain.aggregates.customer_message

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_message.CustomerMessage
import edu.only4.danmuku.domain.aggregates.customer_message.factory.CustomerMessageFactory

/**
 * CustomerMessage aggregate wrapper
 * 用户消息表
 */
class AggCustomerMessage(
    payload: CustomerMessageFactory.Payload? = null,
) : Aggregate.Default<CustomerMessage>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCustomerMessage, UUID>(key)
}

