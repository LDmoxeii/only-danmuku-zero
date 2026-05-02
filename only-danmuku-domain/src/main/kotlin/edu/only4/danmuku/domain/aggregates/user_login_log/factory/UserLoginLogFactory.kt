package edu.only4.danmuku.domain.aggregates.user_login_log.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate

import edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog
import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult
import edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType

import org.springframework.stereotype.Service

/**
 * 用户登录日志;
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/11/20
 */
@Service
@Aggregate(
    aggregate = "UserLoginLog",
    name = "UserLoginLogFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class UserLoginLogFactory : AggregateFactory<UserLoginLogFactory.Payload, UserLoginLog> {

    override fun create(payload: Payload): UserLoginLog {
        return UserLoginLog(
            id = UUID(0L, 0L),
            userId = payload.userId,
            userType = payload.userType,
            loginName = payload.loginName,
            loginType = payload.loginType,
            result = payload.result,
            ip = payload.ip,
            userAgent = payload.userAgent,
            reason = payload.reason,
            occurTime = payload.occurTime,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L
        )
    }

     @Aggregate(
        aggregate = "UserLoginLog",
        name = "UserLoginLogPayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val userId: UUID?,
        val userType: UserType,
        val loginName: String,
        val loginType: LoginType,
        val result: LoginResult,
        val ip: String,
        val userAgent: String?,
        val reason: String?,
        val occurTime: Long,
    ) : AggregatePayload<UserLoginLog>

}

