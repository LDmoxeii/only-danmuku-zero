package edu.only4.danmuku.application.subscribers.domain.customer_focus

import edu.only4.danmuku.domain.aggregates.customer_focus.events.UserFocusedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserFocusedDomainEventSubscriber {

    @EventListener(UserFocusedDomainEvent::class)
    fun on(event: UserFocusedDomainEvent) {
    }
}
