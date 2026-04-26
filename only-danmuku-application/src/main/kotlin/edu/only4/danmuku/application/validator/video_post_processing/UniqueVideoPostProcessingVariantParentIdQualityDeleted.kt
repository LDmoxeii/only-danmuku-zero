package edu.only4.danmuku.application.validator.video_post_processing

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingVariantParentIdQualityDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoPostProcessingVariantParentIdQualityDeleted.Validator::class])
annotation class UniqueVideoPostProcessingVariantParentIdQualityDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val parentIdField: String = "parentId",
    val qualityField: String = "quality",
    val deletedField: String = "deleted",
    val videoPostProcessingVariantIdField: String = "videoPostProcessingVariantId",
) {
    class Validator : ConstraintValidator<UniqueVideoPostProcessingVariantParentIdQualityDeleted, Any> {
        private lateinit var parentIdProperty: String
        private lateinit var qualityProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoPostProcessingVariantIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoPostProcessingVariantParentIdQualityDeleted) {
            parentIdProperty = constraintAnnotation.parentIdField
            qualityProperty = constraintAnnotation.qualityField
            deletedProperty = constraintAnnotation.deletedField
            videoPostProcessingVariantIdProperty = constraintAnnotation.videoPostProcessingVariantIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val parentIdPropertyRef = props[parentIdProperty] ?: return false
                val parentId = parentIdPropertyRef.getter.call(value) as? Long
                val qualityPropertyRef = props[qualityProperty] ?: return false
                val quality = qualityPropertyRef.getter.call(value) as? String
                val qualityTrimmed = quality?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoPostProcessingVariantIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (parentId != null) &&
                    (qualityTrimmed != null && qualityTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Request(
                        parentId = parentId!!,
                        quality = qualityTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoPostProcessingVariantId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
