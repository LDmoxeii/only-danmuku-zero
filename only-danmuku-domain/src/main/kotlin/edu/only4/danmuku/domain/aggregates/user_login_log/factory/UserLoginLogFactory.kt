package edu.only4.danmuku.domain.aggregates.user_login_log.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "UserLoginLog",
    name = "UserLoginLogFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class UserLoginLogFactory : AggregateFactory<UserLoginLogFactory.Payload, UserLoginLog> {

    override fun create(payload: Payload): UserLoginLog {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "UserLoginLog",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<UserLoginLog>
}
