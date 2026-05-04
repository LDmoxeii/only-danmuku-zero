package edu.only4.danmuku.application.subscribers.domain.customer_action

import edu.only4.danmuku.domain.aggregates.customer_action.events.CustomerUnlikedCommentDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerUnlikedCommentDomainEventSubscriber {

    @EventListener(CustomerUnlikedCommentDomainEvent::class)
    fun on(event: CustomerUnlikedCommentDomainEvent) {
    }
}
