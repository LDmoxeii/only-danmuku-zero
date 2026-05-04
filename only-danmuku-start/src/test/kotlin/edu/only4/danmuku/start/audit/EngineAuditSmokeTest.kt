package edu.only4.danmuku.start.audit

import com.only.engine.audit.config.AuditAutoConfiguration
import com.only.engine.audit.core.AuditEntityLifecycleListener
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootTest(
    classes = [EngineAuditSmokeTest.TestApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:engine_audit_smoke;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "only.engine.redis.enable=false",
        "only.engine.oss.enable=false",
        "only.engine.captcha.enable=false",
        "only.engine.sa-token.enable=false",
        "only.engine.security.enable=false"
    ]
)
class EngineAuditSmokeTest(
    @param:Autowired private val applicationContext: ApplicationContext,
    @param:Autowired private val entityManager: EntityManager,
    @param:Autowired private val repository: AuditProbeRepository,
) {

    @Test
    fun `context loads with engine audit and without old jpa auditing bridge`() {
        assertThat(applicationContext.getBean(AuditAutoConfiguration::class.java)).isNotNull
        assertThat(applicationContext.getBean(AuditEntityLifecycleListener::class.java)).isNotNull
        assertThat(applicationContext.containsBean("auditHibernatePropertiesCustomizer")).isTrue
        assertThat(
            applicationContext.getBean(
                "auditHibernatePropertiesCustomizer",
                HibernatePropertiesCustomizer::class.java
            )
        ).isNotNull
        assertThrows(ClassNotFoundException::class.java) {
            Class.forName("edu.only4.danmuku.adapter.domain._share.configure.JpaAuditingConfig")
        }

        val created = repository.saveAndFlush(
            AuditProbeEntity().apply {
                name = "created"
            }
        )

        entityManager.clear()
        val persisted = repository.findById(created.id!!).orElseThrow()
        assertThat(persisted.createTime).isNotNull
        assertThat(persisted.updateTime).isNotNull

        persisted.name = "updated"
        val originalUpdateTime = persisted.updateTime
        repository.saveAndFlush(persisted)

        entityManager.clear()
        val updated = repository.findById(created.id!!).orElseThrow()
        assertThat(updated.updateTime).isNotNull
        assertThat(updated.updateTime).isGreaterThanOrEqualTo(originalUpdateTime)
    }

    @SpringBootApplication(
        proxyBeanMethods = false,
        exclude = [
            com.baomidou.lock.spring.boot.autoconfigure.LockAutoConfiguration::class,
            com.only4.cap4k.ddd.application.distributed.JdbcLockerAutoConfiguration::class,
            com.only4.cap4k.ddd.application.event.IntegrationEventAutoConfiguration::class,
            com.only4.cap4k.ddd.application.request.RequestAutoConfiguration::class,
            com.only4.cap4k.ddd.application.saga.SagaAutoConfiguration::class,
            com.only4.cap4k.ddd.console.DDDConsoleAutoConfiguration::class,
            com.only4.cap4k.ddd.domain.distributed.SnowflakeAutoConfiguration::class,
            com.only4.cap4k.ddd.domain.event.DomainEventAutoConfiguration::class,
            com.only4.cap4k.ddd.domain.repo.JpaRepositoryAutoConfiguration::class,
            org.redisson.spring.starter.RedissonAutoConfigurationV2::class,
            org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration::class,
            org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration::class,
            org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration::class
        ]
    )
    @EnableScheduling
    @EnableJpaRepositories(basePackageClasses = [AuditProbeRepository::class])
    @EntityScan(basePackageClasses = [AuditProbeEntity::class])
    class TestApplication
}
