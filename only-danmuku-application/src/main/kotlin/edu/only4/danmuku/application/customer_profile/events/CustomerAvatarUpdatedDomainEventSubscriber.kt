package edu.only4.danmuku.application.customer_profile.events

import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerAvatarUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerAvatarUpdatedDomainEventSubscriber {

    @EventListener(CustomerAvatarUpdatedDomainEvent::class)
    fun on(event: CustomerAvatarUpdatedDomainEvent) {
    }
}
