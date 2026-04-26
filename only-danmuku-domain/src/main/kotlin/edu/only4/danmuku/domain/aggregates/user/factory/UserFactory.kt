package edu.only4.danmuku.domain.aggregates.user.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.user.User
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "User",
    name = "UserFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class UserFactory : AggregateFactory<UserFactory.Payload, User> {

    override fun create(payload: Payload): User {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "User",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<User>
}
