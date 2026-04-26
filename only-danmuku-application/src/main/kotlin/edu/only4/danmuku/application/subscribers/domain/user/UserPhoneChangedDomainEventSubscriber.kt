package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.UserPhoneChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UserPhoneChangedDomainEventSubscriber {

    @EventListener(UserPhoneChangedDomainEvent::class)
    fun on(event: UserPhoneChangedDomainEvent) {
    }
}
