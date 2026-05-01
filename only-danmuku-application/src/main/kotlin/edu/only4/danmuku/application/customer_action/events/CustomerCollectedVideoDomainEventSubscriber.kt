package edu.only4.danmuku.application.customer_action.events

import edu.only4.danmuku.domain.aggregates.customer_action.events.CustomerCollectedVideoDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerCollectedVideoDomainEventSubscriber {

    @EventListener(CustomerCollectedVideoDomainEvent::class)
    fun on(event: CustomerCollectedVideoDomainEvent) {
    }
}
