package edu.only4.danmuku.application.user_login_log.events

import edu.only4.danmuku.domain.aggregates.user_login_log.events.PasswordInputFailedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PasswordInputFailedDomainEventSubscriber {

    @EventListener(PasswordInputFailedDomainEvent::class)
    fun on(event: PasswordInputFailedDomainEvent) {
    }
}
