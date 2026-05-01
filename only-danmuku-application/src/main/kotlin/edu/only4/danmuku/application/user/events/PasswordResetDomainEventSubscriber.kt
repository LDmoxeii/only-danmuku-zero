package edu.only4.danmuku.application.user.events

import edu.only4.danmuku.domain.aggregates.user.events.PasswordResetDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PasswordResetDomainEventSubscriber {

    @EventListener(PasswordResetDomainEvent::class)
    fun on(event: PasswordResetDomainEvent) {
    }
}
