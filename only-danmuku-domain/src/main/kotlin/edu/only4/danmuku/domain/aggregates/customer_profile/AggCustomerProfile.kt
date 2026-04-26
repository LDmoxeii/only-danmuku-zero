package edu.only4.danmuku.domain.aggregates.customer_profile

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile
import edu.only4.danmuku.domain.aggregates.customer_profile.factory.CustomerProfileFactory

/**
 * CustomerProfile aggregate wrapper
 * 用户信息
 */
class AggCustomerProfile(
    payload: CustomerProfileFactory.Payload? = null,
) : Aggregate.Default<CustomerProfile>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCustomerProfile, Long>(key)
}
