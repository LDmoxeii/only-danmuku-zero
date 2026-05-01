package edu.only4.danmuku.application.customer_action.events

import edu.only4.danmuku.domain.aggregates.customer_action.events.CustomerUndislikedCommentDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerUndislikedCommentDomainEventSubscriber {

    @EventListener(CustomerUndislikedCommentDomainEvent::class)
    fun on(event: CustomerUndislikedCommentDomainEvent) {
    }
}
