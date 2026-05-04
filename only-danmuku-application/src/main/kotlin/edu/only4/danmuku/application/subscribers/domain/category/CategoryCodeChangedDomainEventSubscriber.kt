package edu.only4.danmuku.application.subscribers.domain.category

import edu.only4.danmuku.domain.aggregates.category.events.CategoryCodeChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryCodeChangedDomainEventSubscriber {

    @EventListener(CategoryCodeChangedDomainEvent::class)
    fun on(event: CategoryCodeChangedDomainEvent) {
    }
}
