package edu.only4.danmuku.adapter.domain._share.configure

import edu.only4.danmuku.adapter.support.CurrentUser

import com.only.engine.satoken.utils.LoginHelper
import edu.only4.danmuku.domain._share.audit.AuditSupport
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class JpaAuditingConfig {

    @PostConstruct
    fun wireAuditSupport() {
        // Bridge to domain-side helper for name/ID usage in AuditedEntity
        AuditSupport.register(object : AuditSupport.Provider {
            override fun currentUserId() = CurrentUser.id()
            override fun currentUserName(): String? = LoginHelper.getUserInfo()?.username
        })
    }
}
