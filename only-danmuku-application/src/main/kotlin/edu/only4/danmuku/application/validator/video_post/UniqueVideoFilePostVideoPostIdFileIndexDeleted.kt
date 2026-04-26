package edu.only4.danmuku.application.validator.video_post

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostVideoPostIdFileIndexDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoFilePostVideoPostIdFileIndexDeleted.Validator::class])
annotation class UniqueVideoFilePostVideoPostIdFileIndexDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoPostIdField: String = "videoPostId",
    val fileIndexField: String = "fileIndex",
    val deletedField: String = "deleted",
    val videoFilePostIdField: String = "videoFilePostId",
) {
    class Validator : ConstraintValidator<UniqueVideoFilePostVideoPostIdFileIndexDeleted, Any> {
        private lateinit var videoPostIdProperty: String
        private lateinit var fileIndexProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoFilePostIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoFilePostVideoPostIdFileIndexDeleted) {
            videoPostIdProperty = constraintAnnotation.videoPostIdField
            fileIndexProperty = constraintAnnotation.fileIndexField
            deletedProperty = constraintAnnotation.deletedField
            videoFilePostIdProperty = constraintAnnotation.videoFilePostIdField
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
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoFilePostIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (videoPostId != null) &&
                    (fileIndex != null) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Request(
                        videoPostId = videoPostId!!,
                        fileIndex = fileIndex!!,
                        deleted = deleted!!,
                        excludeVideoFilePostId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
