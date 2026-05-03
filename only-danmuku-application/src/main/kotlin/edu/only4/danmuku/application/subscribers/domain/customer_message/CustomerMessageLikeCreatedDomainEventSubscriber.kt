package edu.only4.danmuku.application.subscribers.domain.customer_message

import edu.only4.danmuku.domain.aggregates.customer_message.events.CustomerMessageLikeCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerMessageLikeCreatedDomainEventSubscriber {

    @EventListener(CustomerMessageLikeCreatedDomainEvent::class)
    fun on(event: CustomerMessageLikeCreatedDomainEvent) {
    }
}
