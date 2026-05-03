package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.UserLoginSuccessfulDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserLoginSuccessfulDomainEventSubscriber {

    @EventListener(UserLoginSuccessfulDomainEvent::class)
    fun on(event: UserLoginSuccessfulDomainEvent) {
    }
}
