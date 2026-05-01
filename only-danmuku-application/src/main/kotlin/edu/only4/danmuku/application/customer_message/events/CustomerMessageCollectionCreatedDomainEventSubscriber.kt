package edu.only4.danmuku.application.customer_message.events

import edu.only4.danmuku.domain.aggregates.customer_message.events.CustomerMessageCollectionCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerMessageCollectionCreatedDomainEventSubscriber {

    @EventListener(CustomerMessageCollectionCreatedDomainEvent::class)
    fun on(event: CustomerMessageCollectionCreatedDomainEvent) {
    }
}
