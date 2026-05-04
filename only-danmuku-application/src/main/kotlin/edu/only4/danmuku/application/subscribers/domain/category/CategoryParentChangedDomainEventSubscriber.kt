package edu.only4.danmuku.application.subscribers.domain.category

import edu.only4.danmuku.domain.aggregates.category.events.CategoryParentChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryParentChangedDomainEventSubscriber {

    @EventListener(CategoryParentChangedDomainEvent::class)
    fun on(event: CategoryParentChangedDomainEvent) {
    }
}
