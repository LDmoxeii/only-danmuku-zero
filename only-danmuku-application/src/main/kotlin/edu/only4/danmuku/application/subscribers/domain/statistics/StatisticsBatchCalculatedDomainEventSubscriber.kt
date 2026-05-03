package edu.only4.danmuku.application.subscribers.domain.statistics

import edu.only4.danmuku.domain.aggregates.statistics.events.StatisticsBatchCalculatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class StatisticsBatchCalculatedDomainEventSubscriber {

    @EventListener(StatisticsBatchCalculatedDomainEvent::class)
    fun on(event: StatisticsBatchCalculatedDomainEvent) {
    }
}
