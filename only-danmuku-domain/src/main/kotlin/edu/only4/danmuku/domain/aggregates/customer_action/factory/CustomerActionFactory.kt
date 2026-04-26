package edu.only4.danmuku.domain.aggregates.customer_action.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_action.CustomerAction
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerAction",
    name = "CustomerActionFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CustomerActionFactory : AggregateFactory<CustomerActionFactory.Payload, CustomerAction> {

    override fun create(payload: Payload): CustomerAction {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "CustomerAction",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<CustomerAction>
}
