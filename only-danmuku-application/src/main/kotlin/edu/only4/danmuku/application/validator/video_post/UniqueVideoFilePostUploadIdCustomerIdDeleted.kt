package edu.only4.danmuku.application.validator.video_post

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostUploadIdCustomerIdDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoFilePostUploadIdCustomerIdDeleted.Validator::class])
annotation class UniqueVideoFilePostUploadIdCustomerIdDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val uploadIdField: String = "uploadId",
    val customerIdField: String = "customerId",
    val deletedField: String = "deleted",
    val videoFilePostIdField: String = "videoFilePostId",
) {
    class Validator : ConstraintValidator<UniqueVideoFilePostUploadIdCustomerIdDeleted, Any> {
        private lateinit var uploadIdProperty: String
        private lateinit var customerIdProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoFilePostIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoFilePostUploadIdCustomerIdDeleted) {
            uploadIdProperty = constraintAnnotation.uploadIdField
            customerIdProperty = constraintAnnotation.customerIdField
            deletedProperty = constraintAnnotation.deletedField
            videoFilePostIdProperty = constraintAnnotation.videoFilePostIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val uploadIdPropertyRef = props[uploadIdProperty] ?: return false
                val uploadId = uploadIdPropertyRef.getter.call(value) as? Long
                val customerIdPropertyRef = props[customerIdProperty] ?: return false
                val customerId = customerIdPropertyRef.getter.call(value) as? Long
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoFilePostIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (uploadId != null) &&
                    (customerId != null) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Request(
                        uploadId = uploadId!!,
                        customerId = customerId!!,
                        deleted = deleted!!,
                        excludeVideoFilePostId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
