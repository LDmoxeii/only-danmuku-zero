package edu.only4.danmuku.application.statistics.events

import edu.only4.danmuku.domain.aggregates.statistics.events.StatisticsInfoAddedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class StatisticsInfoAddedDomainEventSubscriber {

    @EventListener(StatisticsInfoAddedDomainEvent::class)
    fun on(event: StatisticsInfoAddedDomainEvent) {
    }
}
