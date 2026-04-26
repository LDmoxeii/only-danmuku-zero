package edu.only4.danmuku.application.subscribers.domain.category

import edu.only4.danmuku.domain.aggregates.category.events.CategoryDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryDeletedDomainEventSubscriber {

    @EventListener(CategoryDeletedDomainEvent::class)
    fun on(event: CategoryDeletedDomainEvent) {
    }
}
