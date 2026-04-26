package edu.only4.danmuku.domain.aggregates.user.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.user.User

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "User",
    name = "LoginInfoUpdatedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class LoginInfoUpdatedDomainEvent(
    val entity: User,
    val entity: User
) {
}
