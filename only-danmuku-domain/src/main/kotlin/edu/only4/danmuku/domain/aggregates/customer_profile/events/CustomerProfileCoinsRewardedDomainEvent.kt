package edu.only4.danmuku.domain.aggregates.customer_profile.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "CustomerProfile",
    name = "CustomerProfileCoinsRewardedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class CustomerProfileCoinsRewardedDomainEvent(
    val entity: CustomerProfile,
    val amount: Int
) {
}
