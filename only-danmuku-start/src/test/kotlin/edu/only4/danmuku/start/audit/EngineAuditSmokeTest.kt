package edu.only4.danmuku.start.audit

import com.only.engine.audit.config.AuditAutoConfiguration
import com.only.engine.audit.core.AuditEntityLifecycleListener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootTest(
    classes = [EngineAuditSmokeTest.TestApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "spring.main.lazy-initialization=true",
        "spring.autoconfigure.exclude=" +
            "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
            "org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration," +
            "org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration," +
            "org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration," +
            "com.baomidou.lock.spring.boot.autoconfigure.LockAutoConfiguration," +
            "com.only4.cap4k.ddd.domain.repo.JpaRepositoryAutoConfiguration," +
            "com.only4.cap4k.ddd.domain.event.DomainEventAutoConfiguration," +
            "com.only4.cap4k.ddd.application.request.RequestAutoConfiguration," +
            "com.only4.cap4k.ddd.application.saga.SagaAutoConfiguration," +
            "com.only4.cap4k.ddd.application.event.IntegrationEventAutoConfiguration," +
            "com.only4.cap4k.ddd.application.distributed.JdbcLockerAutoConfiguration," +
            "com.only4.cap4k.ddd.domain.distributed.SnowflakeAutoConfiguration," +
            "com.only4.cap4k.ddd.console.DDDConsoleAutoConfiguration," +
            "org.redisson.spring.starter.RedissonAutoConfigurationV2," +
            "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration," +
            "org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration," +
            "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration",
        "only.engine.redis.enable=false",
        "only.engine.oss.enable=false",
        "only.engine.captcha.enable=false",
        "only.engine.sa-token.enable=false",
        "only.engine.security.enable=false"
    ]
)
class EngineAuditSmokeTest(
    @Autowired private val applicationContext: ApplicationContext,
) {

    @Test
    fun `context loads with engine audit and without old jpa auditing bridge`() {
        assertThat(applicationContext.getBean(AuditAutoConfiguration::class.java)).isNotNull
        assertThat(applicationContext.getBean(AuditEntityLifecycleListener::class.java)).isNotNull
        assertThat(applicationContext.containsBean("auditHibernatePropertiesCustomizer")).isTrue
        assertThat(applicationContext.containsBean("jpaAuditingConfig")).isFalse
    }

    @SpringBootConfiguration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    @EnableScheduling
    class TestApplication
}
