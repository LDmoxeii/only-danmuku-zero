package edu.only4.danmuku.start.audit

import com.only.engine.audit.config.AuditAutoConfiguration
import com.only.engine.spi.audit.AuditOperatorProvider
import com.only4.cap4k.ddd.application.distributed.JdbcLockerAutoConfiguration
import com.only4.cap4k.ddd.application.event.IntegrationEventAutoConfiguration
import com.only4.cap4k.ddd.application.request.RequestAutoConfiguration
import com.only4.cap4k.ddd.application.saga.SagaAutoConfiguration
import com.only4.cap4k.ddd.console.DDDConsoleAutoConfiguration
import com.only4.cap4k.ddd.domain.distributed.SnowflakeAutoConfiguration
import com.only4.cap4k.ddd.domain.event.DomainEventAutoConfiguration
import com.only4.cap4k.ddd.domain.repo.JpaRepositoryAutoConfiguration
import edu.only4.danmuku.domain._share.enums.EncryptMethod
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingFile
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingVariant
import edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.Transactional
import org.redisson.spring.starter.RedissonAutoConfigurationV2
import java.util.UUID

@SpringBootTest(
    classes = [
        GeneratedEntityAuditIntegrationTest.TestApplication::class,
        GeneratedEntityAuditIntegrationTest.FixedAuditOperatorConfiguration::class,
    ],
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:generated_entity_audit;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false",
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
@Transactional
class GeneratedEntityAuditIntegrationTest(
    @param:Autowired private val applicationContext: ApplicationContext,
) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Test
    fun `generated entity chain persists audit fields and navigates parent references`() {
        assertThat(applicationContext.getBean(AuditAutoConfiguration::class.java)).isNotNull

        val rootId = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val fileId = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val variantId = UUID.fromString("00000000-0000-0000-0000-000000000003")
        val root = VideoPostProcessing(
            id = rootId,
            videoPostId = UUID.fromString("00000000-0000-0000-0000-000000000101"),
            totalFiles = 1,
            transcodeStatus = ProcessStatus.PROCESSING,
            encryptStatus = ProcessStatus.PENDING,
            transcodeDoneCount = 0,
            encryptDoneCount = 0,
            failedCount = 0,
            lastFailReason = null,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L,
        )
        val file = VideoPostProcessingFile(
            id = fileId,
            parentId = rootId,
            fileIndex = 1,
            uploadId = UUID.fromString("00000000-0000-0000-0000-000000000201"),
            transcodeStatus = ProcessStatus.PROCESSING,
            encryptStatus = ProcessStatus.PENDING,
            encryptMethod = EncryptMethod.HLS_AES_128,
            encryptKeyVersion = null,
            transcodeOutputPrefix = "video/1/",
            transcodeOutputPath = "/tmp/video/1",
            transcodeVariantsJson = null,
            encryptOutputDir = "/tmp/video/1/enc",
            encryptOutputPrefix = null,
            duration = 120,
            fileSize = 4096L,
            failReason = null,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L,
        )
        val variant = VideoPostProcessingVariant(
            id = variantId,
            parentId = fileId,
            quality = "1080p",
            width = 1920,
            height = 1080,
            videoBitrateKbps = 5000,
            audioBitrateKbps = 192,
            bandwidthBps = 5500000,
            playlistPath = "1080p/index.m3u8",
            segmentPrefix = "1080p/",
            segmentDuration = 6,
            transcodeStatus = ProcessStatus.SUCCESS,
            encryptStatus = ProcessStatus.PENDING,
            encryptFailReason = null,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L,
        )
        file.videoPostProcessingVariants.add(variant)
        root.files.add(file)

        entityManager.persist(root)
        entityManager.flush()
        entityManager.clear()

        val persisted = entityManager.find(VideoPostProcessing::class.java, root.id)
        assertThat(persisted).isNotNull
        persisted!!

        assertAuditInsert(persisted)
        val persistedFile = persisted.files.single()
        assertAuditInsert(persistedFile)
        val persistedVariant = persistedFile.videoPostProcessingVariants.single()
        assertAuditInsert(persistedVariant)

        assertThat(persistedFile.videoPostProcessing).isNotNull
        assertThat(persistedFile.videoPostProcessing.id).isEqualTo(persisted.id)
        assertThat(persistedVariant.videoPostProcessingFile).isNotNull
        assertThat(persistedVariant.videoPostProcessingFile.id).isEqualTo(persistedFile.id)

        val originalRootUpdateTime = persisted.updateTime
        val originalFileUpdateTime = persistedFile.updateTime
        setField(persisted, "lastFailReason", "audit-updated")
        setField(persistedFile, "transcodeOutputPath", "/tmp/video/1/master-updated.m3u8")
        entityManager.flush()
        entityManager.clear()

        val updated = entityManager.find(VideoPostProcessing::class.java, persisted.id)
        assertThat(updated).isNotNull
        updated!!
        val updatedFile = updated.files.single()
        assertThat(updated.lastFailReason).isEqualTo("audit-updated")
        assertThat(updatedFile.transcodeOutputPath).isEqualTo("/tmp/video/1/master-updated.m3u8")
        assertThat(updated.updateTime).isNotNull
        assertThat(updated.updateTime).isGreaterThanOrEqualTo(originalRootUpdateTime)
        assertThat(updated.updateUserId).isEqualTo(FIXED_OPERATOR_ID)
        assertThat(updated.updateBy).isEqualTo(FIXED_OPERATOR_NAME)
        assertThat(updatedFile.updateTime).isNotNull
        assertThat(updatedFile.updateTime).isGreaterThanOrEqualTo(originalFileUpdateTime)
        assertThat(updatedFile.updateUserId).isEqualTo(FIXED_OPERATOR_ID)
        assertThat(updatedFile.updateBy).isEqualTo(FIXED_OPERATOR_NAME)
    }

    private fun setField(target: Any, name: String, value: Any?) {
        val field = target::class.java.getDeclaredField(name)
        field.isAccessible = true
        field.set(target, value)
    }

    private fun assertAuditInsert(entity: Any) {
        when (entity) {
            is VideoPostProcessing -> {
                assertThat(entity.createTime).isNotNull
                assertThat(entity.updateTime).isNotNull
                assertThat(entity.createUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.createBy).isEqualTo(FIXED_OPERATOR_NAME)
                assertThat(entity.updateUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.updateBy).isEqualTo(FIXED_OPERATOR_NAME)
            }

            is VideoPostProcessingFile -> {
                assertThat(entity.createTime).isNotNull
                assertThat(entity.updateTime).isNotNull
                assertThat(entity.createUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.createBy).isEqualTo(FIXED_OPERATOR_NAME)
                assertThat(entity.updateUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.updateBy).isEqualTo(FIXED_OPERATOR_NAME)
            }

            is VideoPostProcessingVariant -> {
                assertThat(entity.createTime).isNotNull
                assertThat(entity.updateTime).isNotNull
                assertThat(entity.createUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.createBy).isEqualTo(FIXED_OPERATOR_NAME)
                assertThat(entity.updateUserId).isEqualTo(FIXED_OPERATOR_ID)
                assertThat(entity.updateBy).isEqualTo(FIXED_OPERATOR_NAME)
            }
        }
    }

    @SpringBootApplication(
        proxyBeanMethods = false,
        exclude = [
            com.baomidou.lock.spring.boot.autoconfigure.LockAutoConfiguration::class,
            JdbcLockerAutoConfiguration::class,
            IntegrationEventAutoConfiguration::class,
            RequestAutoConfiguration::class,
            SagaAutoConfiguration::class,
            DDDConsoleAutoConfiguration::class,
            SnowflakeAutoConfiguration::class,
            DomainEventAutoConfiguration::class,
            JpaRepositoryAutoConfiguration::class,
            org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration::class,
            RedissonAutoConfigurationV2::class,
            org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration::class,
            org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration::class,
            org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration::class
        ]
    )
    @EnableScheduling
    @EntityScan(
        basePackageClasses = [
            VideoPostProcessing::class,
            VideoPostProcessingFile::class,
            VideoPostProcessingVariant::class,
        ]
    )
    class TestApplication

    @TestConfiguration(proxyBeanMethods = false)
    class FixedAuditOperatorConfiguration {

        @Bean
        fun auditOperatorProvider(): AuditOperatorProvider = object : AuditOperatorProvider {
            override fun currentOperatorId(): Any? = FIXED_OPERATOR_ID

            override fun currentOperatorName(): String? = FIXED_OPERATOR_NAME
        }
    }

    companion object {
        private val FIXED_OPERATOR_ID: UUID = UUID.fromString("00000000-0000-0000-0000-00000000a001")
        private const val FIXED_OPERATOR_NAME = "audit-tester"
    }
}
