package edu.only4.danmuku.application.subscribers.domain.customer_message

import edu.only4.danmuku.domain.aggregates.customer_message.events.CustomerMessagePrivateCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerMessagePrivateCreatedDomainEventSubscriber {

    @EventListener(CustomerMessagePrivateCreatedDomainEvent::class)
    fun on(event: CustomerMessagePrivateCreatedDomainEvent) {
    }
}
