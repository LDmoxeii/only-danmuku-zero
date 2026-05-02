package edu.only4.danmuku.domain.aggregates.customer_video_series

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_video_series.CustomerVideoSeries
import edu.only4.danmuku.domain.aggregates.customer_video_series.factory.CustomerVideoSeriesFactory

/**
 * CustomerVideoSeries aggregate wrapper
 * 用户视频序列归档
 */
class AggCustomerVideoSeries(
    payload: CustomerVideoSeriesFactory.Payload? = null,
) : Aggregate.Default<CustomerVideoSeries>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCustomerVideoSeries, UUID>(key)
}

