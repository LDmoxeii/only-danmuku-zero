package edu.only4.danmuku.domain.aggregates.statistics.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.statistics.Statistics

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "Statistics",
    name = "StatisticsBatchCalculatedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class StatisticsBatchCalculatedDomainEvent(
    val entity: Statistics,
    val entity: Statistics
) {
}
