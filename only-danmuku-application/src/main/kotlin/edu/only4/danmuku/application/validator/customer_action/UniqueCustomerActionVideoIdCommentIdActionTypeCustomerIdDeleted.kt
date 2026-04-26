package edu.only4.danmuku.application.validator.customer_action

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.customer_action.UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeleted.Validator::class])
annotation class UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val commentIdField: String = "commentId",
    val actionTypeField: String = "actionType",
    val customerIdField: String = "customerId",
    val deletedField: String = "deleted",
    val customerActionIdField: String = "customerActionId",
) {
    class Validator : ConstraintValidator<UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeleted, Any> {
        private lateinit var videoIdProperty: String
        private lateinit var commentIdProperty: String
        private lateinit var actionTypeProperty: String
        private lateinit var customerIdProperty: String
        private lateinit var deletedProperty: String
        private lateinit var customerActionIdProperty: String

        override fun initialize(constraintAnnotation: UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeleted) {
            videoIdProperty = constraintAnnotation.videoIdField
            commentIdProperty = constraintAnnotation.commentIdField
            actionTypeProperty = constraintAnnotation.actionTypeField
            customerIdProperty = constraintAnnotation.customerIdField
            deletedProperty = constraintAnnotation.deletedField
            customerActionIdProperty = constraintAnnotation.customerActionIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val videoIdPropertyRef = props[videoIdProperty] ?: return false
                val videoId = videoIdPropertyRef.getter.call(value) as? Long
                val commentIdPropertyRef = props[commentIdProperty] ?: return false
                val commentId = commentIdPropertyRef.getter.call(value) as? Long
                val actionTypePropertyRef = props[actionTypeProperty] ?: return false
                val actionType = actionTypePropertyRef.getter.call(value) as? Int
                val customerIdPropertyRef = props[customerIdProperty] ?: return false
                val customerId = customerIdPropertyRef.getter.call(value) as? Long
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[customerActionIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (videoId != null) &&
                    (commentId != null) &&
                    (actionType != null) &&
                    (customerId != null) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueCustomerActionVideoIdCommentIdActionTypeCustomerIdDeletedQry.Request(
                        videoId = videoId!!,
                        commentId = commentId!!,
                        actionType = actionType!!,
                        customerId = customerId!!,
                        deleted = deleted!!,
                        excludeCustomerActionId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
