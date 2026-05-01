package edu.only4.danmuku.application.customer_profile.events

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
