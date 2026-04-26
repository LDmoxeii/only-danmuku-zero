package edu.only4.danmuku.application.validator.video

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video.UniqueVideoFileVariantFileIdQualityDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoFileVariantFileIdQualityDeleted.Validator::class])
annotation class UniqueVideoFileVariantFileIdQualityDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val fileIdField: String = "fileId",
    val qualityField: String = "quality",
    val deletedField: String = "deleted",
    val videoFileVariantIdField: String = "videoFileVariantId",
) {
    class Validator : ConstraintValidator<UniqueVideoFileVariantFileIdQualityDeleted, Any> {
        private lateinit var fileIdProperty: String
        private lateinit var qualityProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoFileVariantIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoFileVariantFileIdQualityDeleted) {
            fileIdProperty = constraintAnnotation.fileIdField
            qualityProperty = constraintAnnotation.qualityField
            deletedProperty = constraintAnnotation.deletedField
            videoFileVariantIdProperty = constraintAnnotation.videoFileVariantIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val fileIdPropertyRef = props[fileIdProperty] ?: return false
                val fileId = fileIdPropertyRef.getter.call(value) as? Long
                val qualityPropertyRef = props[qualityProperty] ?: return false
                val quality = qualityPropertyRef.getter.call(value) as? String
                val qualityTrimmed = quality?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoFileVariantIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (fileId != null) &&
                    (qualityTrimmed != null && qualityTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoFileVariantFileIdQualityDeletedQry.Request(
                        fileId = fileId!!,
                        quality = qualityTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoFileVariantId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
