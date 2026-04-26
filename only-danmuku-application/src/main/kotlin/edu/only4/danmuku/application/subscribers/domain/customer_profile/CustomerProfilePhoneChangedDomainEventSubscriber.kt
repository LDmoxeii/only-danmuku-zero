package edu.only4.danmuku.application.subscribers.domain.customer_profile

import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfilePhoneChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerProfilePhoneChangedDomainEventSubscriber {

    @EventListener(CustomerProfilePhoneChangedDomainEvent::class)
    fun on(event: CustomerProfilePhoneChangedDomainEvent) {
    }
}
