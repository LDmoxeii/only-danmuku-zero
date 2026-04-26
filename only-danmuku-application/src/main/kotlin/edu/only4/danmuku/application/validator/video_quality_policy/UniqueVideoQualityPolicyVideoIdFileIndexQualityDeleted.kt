package edu.only4.danmuku.application.validator.video_quality_policy

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_quality_policy.UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoQualityPolicyVideoIdFileIndexQualityDeleted.Validator::class])
annotation class UniqueVideoQualityPolicyVideoIdFileIndexQualityDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val fileIndexField: String = "fileIndex",
    val qualityField: String = "quality",
    val deletedField: String = "deleted",
    val videoQualityPolicyIdField: String = "videoQualityPolicyId",
) {
    class Validator : ConstraintValidator<UniqueVideoQualityPolicyVideoIdFileIndexQualityDeleted, Any> {
        private lateinit var videoIdProperty: String
        private lateinit var fileIndexProperty: String
        private lateinit var qualityProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoQualityPolicyIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoQualityPolicyVideoIdFileIndexQualityDeleted) {
            videoIdProperty = constraintAnnotation.videoIdField
            fileIndexProperty = constraintAnnotation.fileIndexField
            qualityProperty = constraintAnnotation.qualityField
            deletedProperty = constraintAnnotation.deletedField
            videoQualityPolicyIdProperty = constraintAnnotation.videoQualityPolicyIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val videoIdPropertyRef = props[videoIdProperty] ?: return false
                val videoId = videoIdPropertyRef.getter.call(value) as? Long
                val fileIndexPropertyRef = props[fileIndexProperty] ?: return false
                val fileIndex = fileIndexPropertyRef.getter.call(value) as? Int
                val qualityPropertyRef = props[qualityProperty] ?: return false
                val quality = qualityPropertyRef.getter.call(value) as? String
                val qualityTrimmed = quality?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoQualityPolicyIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (videoId != null) &&
                    (fileIndex != null) &&
                    (qualityTrimmed != null && qualityTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Request(
                        videoId = videoId!!,
                        fileIndex = fileIndex!!,
                        quality = qualityTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoQualityPolicyId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
