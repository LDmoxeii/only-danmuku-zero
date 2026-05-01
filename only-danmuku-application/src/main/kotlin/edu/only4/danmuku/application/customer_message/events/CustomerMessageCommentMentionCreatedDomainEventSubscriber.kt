package edu.only4.danmuku.application.customer_message.events

import edu.only4.danmuku.domain.aggregates.customer_message.events.CustomerMessageCommentMentionCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerMessageCommentMentionCreatedDomainEventSubscriber {

    @EventListener(CustomerMessageCommentMentionCreatedDomainEvent::class)
    fun on(event: CustomerMessageCommentMentionCreatedDomainEvent) {
    }
}
