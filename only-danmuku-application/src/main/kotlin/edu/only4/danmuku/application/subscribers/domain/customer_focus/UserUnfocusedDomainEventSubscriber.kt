package edu.only4.danmuku.application.subscribers.domain.customer_focus

import edu.only4.danmuku.domain.aggregates.customer_focus.events.UserUnfocusedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserUnfocusedDomainEventSubscriber {

    @EventListener(UserUnfocusedDomainEvent::class)
    fun on(event: UserUnfocusedDomainEvent) {
    }
}
