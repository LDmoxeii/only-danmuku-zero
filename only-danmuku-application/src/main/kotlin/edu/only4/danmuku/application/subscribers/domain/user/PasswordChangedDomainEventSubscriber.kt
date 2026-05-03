package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.PasswordChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PasswordChangedDomainEventSubscriber {

    @EventListener(PasswordChangedDomainEvent::class)
    fun on(event: PasswordChangedDomainEvent) {
    }
}
