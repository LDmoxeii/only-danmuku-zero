package edu.only4.danmuku.domain.aggregates.customer_message.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_message.CustomerMessage
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerMessage",
    name = "CustomerMessageFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CustomerMessageFactory : AggregateFactory<CustomerMessageFactory.Payload, CustomerMessage> {

    override fun create(payload: Payload): CustomerMessage {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "CustomerMessage",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<CustomerMessage>
}
