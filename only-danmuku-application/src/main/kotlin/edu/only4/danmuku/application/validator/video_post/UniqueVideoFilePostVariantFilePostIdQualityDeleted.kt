package edu.only4.danmuku.application.validator.video_post

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostVariantFilePostIdQualityDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoFilePostVariantFilePostIdQualityDeleted.Validator::class])
annotation class UniqueVideoFilePostVariantFilePostIdQualityDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val filePostIdField: String = "filePostId",
    val qualityField: String = "quality",
    val deletedField: String = "deleted",
    val videoFilePostVariantIdField: String = "videoFilePostVariantId",
) {
    class Validator : ConstraintValidator<UniqueVideoFilePostVariantFilePostIdQualityDeleted, Any> {
        private lateinit var filePostIdProperty: String
        private lateinit var qualityProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoFilePostVariantIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoFilePostVariantFilePostIdQualityDeleted) {
            filePostIdProperty = constraintAnnotation.filePostIdField
            qualityProperty = constraintAnnotation.qualityField
            deletedProperty = constraintAnnotation.deletedField
            videoFilePostVariantIdProperty = constraintAnnotation.videoFilePostVariantIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val filePostIdPropertyRef = props[filePostIdProperty] ?: return false
                val filePostId = filePostIdPropertyRef.getter.call(value) as? Long
                val qualityPropertyRef = props[qualityProperty] ?: return false
                val quality = qualityPropertyRef.getter.call(value) as? String
                val qualityTrimmed = quality?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoFilePostVariantIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (filePostId != null) &&
                    (qualityTrimmed != null && qualityTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Request(
                        filePostId = filePostId!!,
                        quality = qualityTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoFilePostVariantId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
