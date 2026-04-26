package edu.only4.danmuku.domain.aggregates.customer_focus

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_focus.CustomerFocus
import edu.only4.danmuku.domain.aggregates.customer_focus.factory.CustomerFocusFactory

/**
 * CustomerFocus aggregate wrapper
 * 用户关注
 */
class AggCustomerFocus(
    payload: CustomerFocusFactory.Payload? = null,
) : Aggregate.Default<CustomerFocus>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCustomerFocus, Long>(key)
}
