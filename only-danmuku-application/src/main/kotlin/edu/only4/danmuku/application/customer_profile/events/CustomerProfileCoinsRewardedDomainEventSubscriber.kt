package edu.only4.danmuku.application.customer_profile.events

import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfileCoinsRewardedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerProfileCoinsRewardedDomainEventSubscriber {

    @EventListener(CustomerProfileCoinsRewardedDomainEvent::class)
    fun on(event: CustomerProfileCoinsRewardedDomainEvent) {
    }
}
