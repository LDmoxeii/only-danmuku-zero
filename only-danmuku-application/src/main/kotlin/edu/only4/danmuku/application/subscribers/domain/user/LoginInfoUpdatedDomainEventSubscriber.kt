package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.LoginInfoUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class LoginInfoUpdatedDomainEventSubscriber {

    @EventListener(LoginInfoUpdatedDomainEvent::class)
    fun on(event: LoginInfoUpdatedDomainEvent) {
    }
}
