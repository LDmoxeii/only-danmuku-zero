package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 硬币余额不足，无法修改昵称
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NicknameChangeAllowed.Validator::class])
@MustBeDocumented
annotation class NicknameChangeAllowed(
    val message: String = "硬币余额不足，无法修改昵称",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val userIdField: String = "customerId",
    val nicknameField: String = "nickName"
) {
    class Validator : ConstraintValidator<NicknameChangeAllowed, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
