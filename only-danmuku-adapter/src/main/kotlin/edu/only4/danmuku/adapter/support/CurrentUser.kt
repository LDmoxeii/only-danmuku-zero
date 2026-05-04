package edu.only4.danmuku.adapter.support

import com.only.engine.satoken.utils.LoginHelper
import java.util.UUID

object CurrentUser {
    fun id(): UUID? = LoginHelper.getUserInfo()?.id?.toUuidOrNull()

    fun requiredId(): UUID = id()
        ?: error("Current login user id is not a UUID")

    fun extraUuid(key: String): UUID? = LoginHelper.getUserInfo()
        ?.extra
        ?.get(key)
        ?.toUuidOrNull()

    private fun Any.toUuidOrNull(): UUID? = when (this) {
        is UUID -> this
        is String -> runCatching { UUID.fromString(this) }.getOrNull()
        else -> null
    }
}
