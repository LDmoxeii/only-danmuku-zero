package edu.only4.danmuku.application.validator.customer_profile

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfileNickNameDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueCustomerProfileNickNameDeleted.Validator::class])
annotation class UniqueCustomerProfileNickNameDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val nickNameField: String = "nickName",
    val deletedField: String = "deleted",
    val customerProfileIdField: String = "customerProfileId",
) {
    class Validator : ConstraintValidator<UniqueCustomerProfileNickNameDeleted, Any> {
        private lateinit var nickNameProperty: String
        private lateinit var deletedProperty: String
        private lateinit var customerProfileIdProperty: String

        override fun initialize(constraintAnnotation: UniqueCustomerProfileNickNameDeleted) {
            nickNameProperty = constraintAnnotation.nickNameField
            deletedProperty = constraintAnnotation.deletedField
            customerProfileIdProperty = constraintAnnotation.customerProfileIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val nickNamePropertyRef = props[nickNameProperty] ?: return false
                val nickName = nickNamePropertyRef.getter.call(value) as? String
                val nickNameTrimmed = nickName?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[customerProfileIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (nickNameTrimmed != null && nickNameTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueCustomerProfileNickNameDeletedQry.Request(
                        nickName = nickNameTrimmed!!,
                        deleted = deleted!!,
                        excludeCustomerProfileId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
