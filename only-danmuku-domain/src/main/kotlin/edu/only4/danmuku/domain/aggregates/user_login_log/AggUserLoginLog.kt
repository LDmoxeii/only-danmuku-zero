package edu.only4.danmuku.domain.aggregates.user_login_log

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog
import edu.only4.danmuku.domain.aggregates.user_login_log.factory.UserLoginLogFactory

/**
 * UserLoginLog aggregate wrapper
 * 用户登录日志
 */
class AggUserLoginLog(
    payload: UserLoginLogFactory.Payload? = null,
) : Aggregate.Default<UserLoginLog>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggUserLoginLog, UUID>(key)
}

