package edu.only4.danmuku.domain.aggregates.user_login_log.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "UserLoginLog",
    name = "PasswordInputFailedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class PasswordInputFailedDomainEvent(
    val entity: UserLoginLog,
    val entity: UserLoginLog
) {
}
