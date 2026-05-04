package edu.only4.danmuku.domain.aggregates.statistics

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.statistics.Statistics
import edu.only4.danmuku.domain.aggregates.statistics.factory.StatisticsFactory

/**
 * Statistics aggregate wrapper
 * 统计信息
 */
class AggStatistics(
    payload: StatisticsFactory.Payload? = null,
) : Aggregate.Default<Statistics>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggStatistics, UUID>(key)
}

