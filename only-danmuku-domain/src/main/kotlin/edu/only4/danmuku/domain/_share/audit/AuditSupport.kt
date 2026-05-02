package edu.only4.danmuku.domain._share.audit

import java.util.UUID

/**
 * Lightweight auditing support for domain entities.
 * Adapter layer should register a Provider at startup to supply current user info.
 */
object AuditSupport {
    interface Provider {
        fun currentUserId(): UUID?
        fun currentUserName(): String?
    }

    @Volatile
    private var provider: Provider? = null

    fun register(provider: Provider) {
        this.provider = provider
    }

    fun currentUserId(): UUID? = provider?.currentUserId()
    fun currentUserName(): String? = provider?.currentUserName()
}
