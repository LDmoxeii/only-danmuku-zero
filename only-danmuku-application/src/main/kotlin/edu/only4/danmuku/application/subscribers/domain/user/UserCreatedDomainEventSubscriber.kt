package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.UserCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserCreatedDomainEventSubscriber {

    @EventListener(UserCreatedDomainEvent::class)
    fun on(event: UserCreatedDomainEvent) {
    }
}
