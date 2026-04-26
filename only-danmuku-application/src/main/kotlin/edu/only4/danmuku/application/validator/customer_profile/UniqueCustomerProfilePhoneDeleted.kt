package edu.only4.danmuku.application.validator.customer_profile

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfilePhoneDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueCustomerProfilePhoneDeleted.Validator::class])
annotation class UniqueCustomerProfilePhoneDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val phoneField: String = "phone",
    val deletedField: String = "deleted",
    val customerProfileIdField: String = "customerProfileId",
) {
    class Validator : ConstraintValidator<UniqueCustomerProfilePhoneDeleted, Any> {
        private lateinit var phoneProperty: String
        private lateinit var deletedProperty: String
        private lateinit var customerProfileIdProperty: String

        override fun initialize(constraintAnnotation: UniqueCustomerProfilePhoneDeleted) {
            phoneProperty = constraintAnnotation.phoneField
            deletedProperty = constraintAnnotation.deletedField
            customerProfileIdProperty = constraintAnnotation.customerProfileIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val phonePropertyRef = props[phoneProperty] ?: return false
                val phone = phonePropertyRef.getter.call(value) as? String
                val phoneTrimmed = phone?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[customerProfileIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (phoneTrimmed != null && phoneTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueCustomerProfilePhoneDeletedQry.Request(
                        phone = phoneTrimmed!!,
                        deleted = deleted!!,
                        excludeCustomerProfileId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
