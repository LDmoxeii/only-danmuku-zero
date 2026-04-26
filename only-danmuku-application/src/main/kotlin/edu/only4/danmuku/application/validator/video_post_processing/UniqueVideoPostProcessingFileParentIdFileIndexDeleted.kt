package edu.only4.danmuku.application.validator.video_post_processing

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoPostProcessingFileParentIdFileIndexDeleted.Validator::class])
annotation class UniqueVideoPostProcessingFileParentIdFileIndexDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val parentIdField: String = "parentId",
    val fileIndexField: String = "fileIndex",
    val deletedField: String = "deleted",
    val videoPostProcessingFileIdField: String = "videoPostProcessingFileId",
) {
    class Validator : ConstraintValidator<UniqueVideoPostProcessingFileParentIdFileIndexDeleted, Any> {
        private lateinit var parentIdProperty: String
        private lateinit var fileIndexProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoPostProcessingFileIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoPostProcessingFileParentIdFileIndexDeleted) {
            parentIdProperty = constraintAnnotation.parentIdField
            fileIndexProperty = constraintAnnotation.fileIndexField
            deletedProperty = constraintAnnotation.deletedField
            videoPostProcessingFileIdProperty = constraintAnnotation.videoPostProcessingFileIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val parentIdPropertyRef = props[parentIdProperty] ?: return false
                val parentId = parentIdPropertyRef.getter.call(value) as? Long
                val fileIndexPropertyRef = props[fileIndexProperty] ?: return false
                val fileIndex = fileIndexPropertyRef.getter.call(value) as? Int
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoPostProcessingFileIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (parentId != null) &&
                    (fileIndex != null) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Request(
                        parentId = parentId!!,
                        fileIndex = fileIndex!!,
                        deleted = deleted!!,
                        excludeVideoPostProcessingFileId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
