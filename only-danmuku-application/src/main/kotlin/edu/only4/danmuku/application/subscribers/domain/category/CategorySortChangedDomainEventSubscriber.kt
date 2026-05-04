package edu.only4.danmuku.application.subscribers.domain.category

import edu.only4.danmuku.domain.aggregates.category.events.CategorySortChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategorySortChangedDomainEventSubscriber {

    @EventListener(CategorySortChangedDomainEvent::class)
    fun on(event: CategorySortChangedDomainEvent) {
    }
}
