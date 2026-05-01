package edu.only4.danmuku.application.category.events

import edu.only4.danmuku.domain.aggregates.category.events.CategoryCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryCreatedDomainEventSubscriber {

    @EventListener(CategoryCreatedDomainEvent::class)
    fun on(event: CategoryCreatedDomainEvent) {
    }
}
