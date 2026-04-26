package edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.UserAbnormalOperationLog
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.factory.UserAbnormalOperationLogFactory

/**
 * UserAbnormalOperationLog aggregate wrapper
 * 用户异常操作日志
 */
class AggUserAbnormalOperationLog(
    payload: UserAbnormalOperationLogFactory.Payload? = null,
) : Aggregate.Default<UserAbnormalOperationLog>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggUserAbnormalOperationLog, Long>(key)
}
