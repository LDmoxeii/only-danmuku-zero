package edu.only4.danmuku.start.audit

import com.only.engine.audit.config.AuditAutoConfiguration
import com.only.engine.audit.core.AuditEntityLifecycleListener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class EngineAuditSmokeTest {

    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(AuditAutoConfiguration::class.java))
        .withPropertyValues(
            "only.engine.audit.enable=true",
            "only.engine.sa-token.enable=true"
        )

    @Test
    fun `context loads with engine audit and without old jpa auditing bridge`() {
        contextRunner.run { context ->
            assertThat(context).hasSingleBean(AuditAutoConfiguration::class.java)
            assertThat(context).hasSingleBean(AuditEntityLifecycleListener::class.java)
            assertThat(context).doesNotHaveBean("jpaAuditingConfig")
        }
    }
}
