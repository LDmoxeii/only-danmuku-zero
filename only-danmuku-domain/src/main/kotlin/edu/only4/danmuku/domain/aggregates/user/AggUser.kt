package edu.only4.danmuku.domain.aggregates.user

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.user.User
import edu.only4.danmuku.domain.aggregates.user.factory.UserFactory

/**
 * User aggregate wrapper
 * 帐号
 */
class AggUser(
    payload: UserFactory.Payload? = null,
) : Aggregate.Default<User>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggUser, UUID>(key)
}

