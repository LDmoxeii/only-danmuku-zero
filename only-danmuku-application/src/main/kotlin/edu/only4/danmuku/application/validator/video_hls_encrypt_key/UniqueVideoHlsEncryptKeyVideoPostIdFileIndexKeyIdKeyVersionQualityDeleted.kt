package edu.only4.danmuku.application.validator.video_hls_encrypt_key

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_hls_encrypt_key.UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeleted.Validator::class])
annotation class UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoPostIdField: String = "videoPostId",
    val fileIndexField: String = "fileIndex",
    val keyIdField: String = "keyId",
    val keyVersionField: String = "keyVersion",
    val qualityField: String = "quality",
    val deletedField: String = "deleted",
    val videoHlsEncryptKeyIdField: String = "videoHlsEncryptKeyId",
) {
    class Validator : ConstraintValidator<UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeleted, Any> {
        private lateinit var videoPostIdProperty: String
        private lateinit var fileIndexProperty: String
        private lateinit var keyIdProperty: String
        private lateinit var keyVersionProperty: String
        private lateinit var qualityProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoHlsEncryptKeyIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeleted) {
            videoPostIdProperty = constraintAnnotation.videoPostIdField
            fileIndexProperty = constraintAnnotation.fileIndexField
            keyIdProperty = constraintAnnotation.keyIdField
            keyVersionProperty = constraintAnnotation.keyVersionField
            qualityProperty = constraintAnnotation.qualityField
            deletedProperty = constraintAnnotation.deletedField
            videoHlsEncryptKeyIdProperty = constraintAnnotation.videoHlsEncryptKeyIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val videoPostIdPropertyRef = props[videoPostIdProperty] ?: return false
                val videoPostId = videoPostIdPropertyRef.getter.call(value) as? Long
                val fileIndexPropertyRef = props[fileIndexProperty] ?: return false
                val fileIndex = fileIndexPropertyRef.getter.call(value) as? Int
                val keyIdPropertyRef = props[keyIdProperty] ?: return false
                val keyId = keyIdPropertyRef.getter.call(value) as? String
                val keyIdTrimmed = keyId?.trim()
                val keyVersionPropertyRef = props[keyVersionProperty] ?: return false
                val keyVersion = keyVersionPropertyRef.getter.call(value) as? Int
                val qualityPropertyRef = props[qualityProperty] ?: return false
                val quality = qualityPropertyRef.getter.call(value) as? String
                val qualityTrimmed = quality?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoHlsEncryptKeyIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (videoPostId != null) &&
                    (fileIndex != null) &&
                    (keyIdTrimmed != null && keyIdTrimmed.isNotBlank()) &&
                    (keyVersion != null) &&
                    (qualityTrimmed != null && qualityTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Request(
                        videoPostId = videoPostId!!,
                        fileIndex = fileIndex!!,
                        keyId = keyIdTrimmed!!,
                        keyVersion = keyVersion!!,
                        quality = qualityTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoHlsEncryptKeyId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
