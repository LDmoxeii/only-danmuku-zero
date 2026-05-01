package edu.only4.danmuku.domain.aggregates.customer_profile.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerProfile",
    name = "CustomerProfileFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CustomerProfileFactory : AggregateFactory<CustomerProfileFactory.Payload, CustomerProfile> {

    override fun create(payload: Payload): CustomerProfile {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "CustomerProfile",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<CustomerProfile>
}
