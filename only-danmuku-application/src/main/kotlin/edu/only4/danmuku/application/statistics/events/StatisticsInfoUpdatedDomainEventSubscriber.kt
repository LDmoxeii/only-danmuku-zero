package edu.only4.danmuku.application.statistics.events

import edu.only4.danmuku.domain.aggregates.statistics.events.StatisticsInfoUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class StatisticsInfoUpdatedDomainEventSubscriber {

    @EventListener(StatisticsInfoUpdatedDomainEvent::class)
    fun on(event: StatisticsInfoUpdatedDomainEvent) {
    }
}
