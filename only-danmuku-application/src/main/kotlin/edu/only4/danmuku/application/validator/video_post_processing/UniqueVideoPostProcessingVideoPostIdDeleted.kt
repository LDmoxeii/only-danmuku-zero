package edu.only4.danmuku.application.validator.video_post_processing

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingVideoPostIdDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoPostProcessingVideoPostIdDeleted.Validator::class])
annotation class UniqueVideoPostProcessingVideoPostIdDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoPostIdField: String = "videoPostId",
    val deletedField: String = "deleted",
    val videoPostProcessingIdField: String = "videoPostProcessingId",
) {
    class Validator : ConstraintValidator<UniqueVideoPostProcessingVideoPostIdDeleted, Any> {
        private lateinit var videoPostIdProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoPostProcessingIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoPostProcessingVideoPostIdDeleted) {
            videoPostIdProperty = constraintAnnotation.videoPostIdField
            deletedProperty = constraintAnnotation.deletedField
            videoPostProcessingIdProperty = constraintAnnotation.videoPostProcessingIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val videoPostIdPropertyRef = props[videoPostIdProperty] ?: return false
                val videoPostId = videoPostIdPropertyRef.getter.call(value) as? Long
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoPostProcessingIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (videoPostId != null) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoPostProcessingVideoPostIdDeletedQry.Request(
                        videoPostId = videoPostId!!,
                        deleted = deleted!!,
                        excludeVideoPostProcessingId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
