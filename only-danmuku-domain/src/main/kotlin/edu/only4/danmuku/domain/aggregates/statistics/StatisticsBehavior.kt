package edu.only4.danmuku.domain.aggregates.statistics

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.statistics.events.StatisticsCountUpdatedDomainEvent

fun Statistics.updateCount(delta: Int) {
    statisticsCount = (statisticsCount ?: 0) + delta
    events().attach(this) { StatisticsCountUpdatedDomainEvent(this) }
}
