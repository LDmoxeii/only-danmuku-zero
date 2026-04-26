package edu.only4.danmuku.domain.aggregates.customer_video_series.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_video_series.CustomerVideoSeries
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerVideoSeries",
    name = "CustomerVideoSeriesFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CustomerVideoSeriesFactory : AggregateFactory<CustomerVideoSeriesFactory.Payload, CustomerVideoSeries> {

    override fun create(payload: Payload): CustomerVideoSeries {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "CustomerVideoSeries",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<CustomerVideoSeries>
}
