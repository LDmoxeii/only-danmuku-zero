package edu.only4.danmuku.application.validator.user

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.user.UniqueUserEmailDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueUserEmailDeleted.Validator::class])
annotation class UniqueUserEmailDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val emailField: String = "email",
    val deletedField: String = "deleted",
    val userIdField: String = "userId",
) {
    class Validator : ConstraintValidator<UniqueUserEmailDeleted, Any> {
        private lateinit var emailProperty: String
        private lateinit var deletedProperty: String
        private lateinit var userIdProperty: String

        override fun initialize(constraintAnnotation: UniqueUserEmailDeleted) {
            emailProperty = constraintAnnotation.emailField
            deletedProperty = constraintAnnotation.deletedField
            userIdProperty = constraintAnnotation.userIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val emailPropertyRef = props[emailProperty] ?: return false
                val email = emailPropertyRef.getter.call(value) as? String
                val emailTrimmed = email?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[userIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (emailTrimmed != null && emailTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueUserEmailDeletedQry.Request(
                        email = emailTrimmed!!,
                        deleted = deleted!!,
                        excludeUserId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
