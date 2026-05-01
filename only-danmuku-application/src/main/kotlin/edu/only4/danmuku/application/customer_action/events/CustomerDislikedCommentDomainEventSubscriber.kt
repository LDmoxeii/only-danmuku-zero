package edu.only4.danmuku.application.customer_action.events

import edu.only4.danmuku.domain.aggregates.customer_action.events.CustomerDislikedCommentDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerDislikedCommentDomainEventSubscriber {

    @EventListener(CustomerDislikedCommentDomainEvent::class)
    fun on(event: CustomerDislikedCommentDomainEvent) {
    }
}
