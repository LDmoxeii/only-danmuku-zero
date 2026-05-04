package edu.only4.danmuku.domain.aggregates.customer_profile.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate

import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile

import org.springframework.stereotype.Service

/**
 * 用户信息;
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
@Service
@Aggregate(
    aggregate = "CustomerProfile",
    name = "CustomerProfileFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CustomerProfileFactory : AggregateFactory<CustomerProfileFactory.Payload, CustomerProfile> {

    override fun create(entityPayload: Payload): CustomerProfile {
        return CustomerProfile(
            id = UUID(0L, 0L),
            userId = entityPayload.userid,
            nickName = entityPayload.nickName,
            avatar = null,
            email = entityPayload.email,
            phone = null,
            sex = edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType.UNKNOWN,
            birthday = null,
            school = null,
            personIntroduction = null,
            noticeInfo = null,
            totalCoinCount = entityPayload.registerCoinCount,
            currentCoinCount = entityPayload.registerCoinCount,
            theme = edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType.UNKNOW,
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
        aggregate = "CustomerProfile",
        name = "CustomerProfilePayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val userid: UUID,
        val nickName: String,
        val email: String,
        val registerCoinCount: Int,
    ) : AggregatePayload<CustomerProfile>

}

