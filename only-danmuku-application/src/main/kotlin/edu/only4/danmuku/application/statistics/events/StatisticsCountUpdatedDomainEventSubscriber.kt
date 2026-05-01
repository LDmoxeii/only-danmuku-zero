package edu.only4.danmuku.application.statistics.events

import edu.only4.danmuku.domain.aggregates.statistics.events.StatisticsCountUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class StatisticsCountUpdatedDomainEventSubscriber {

    @EventListener(StatisticsCountUpdatedDomainEvent::class)
    fun on(event: StatisticsCountUpdatedDomainEvent) {
    }
}
