package edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.UserAbnormalOperationLog
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "UserAbnormalOperationLog",
    name = "UserAbnormalOperationLogFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class UserAbnormalOperationLogFactory : AggregateFactory<UserAbnormalOperationLogFactory.Payload, UserAbnormalOperationLog> {

    override fun create(payload: Payload): UserAbnormalOperationLog {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "UserAbnormalOperationLog",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<UserAbnormalOperationLog>
}
