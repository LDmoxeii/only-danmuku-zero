package edu.only4.danmuku.application.subscribers.domain.category

import edu.only4.danmuku.domain.aggregates.category.events.CategoryNodePathUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryNodePathUpdatedDomainEventSubscriber {

    @EventListener(CategoryNodePathUpdatedDomainEvent::class)
    fun on(event: CategoryNodePathUpdatedDomainEvent) {
    }
}
