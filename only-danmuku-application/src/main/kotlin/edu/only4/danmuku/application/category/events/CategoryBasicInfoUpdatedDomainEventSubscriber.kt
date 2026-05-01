package edu.only4.danmuku.application.category.events

import edu.only4.danmuku.domain.aggregates.category.events.CategoryBasicInfoUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CategoryBasicInfoUpdatedDomainEventSubscriber {

    @EventListener(CategoryBasicInfoUpdatedDomainEvent::class)
    fun on(event: CategoryBasicInfoUpdatedDomainEvent) {
    }
}
