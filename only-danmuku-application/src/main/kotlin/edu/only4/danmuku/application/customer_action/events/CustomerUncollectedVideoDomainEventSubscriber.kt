package edu.only4.danmuku.application.customer_action.events

import edu.only4.danmuku.domain.aggregates.customer_action.events.CustomerUncollectedVideoDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CustomerUncollectedVideoDomainEventSubscriber {

    @EventListener(CustomerUncollectedVideoDomainEvent::class)
    fun on(event: CustomerUncollectedVideoDomainEvent) {
    }
}
