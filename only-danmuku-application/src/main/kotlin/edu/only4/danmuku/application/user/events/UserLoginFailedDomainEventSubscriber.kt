package edu.only4.danmuku.application.user.events

import edu.only4.danmuku.domain.aggregates.user.events.UserLoginFailedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserLoginFailedDomainEventSubscriber {

    @EventListener(UserLoginFailedDomainEvent::class)
    fun on(event: UserLoginFailedDomainEvent) {
    }
}
