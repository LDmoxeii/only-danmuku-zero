package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 无效的审核状态
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidAuditStatus.Validator::class])
@MustBeDocumented
annotation class ValidAuditStatus(
    val message: String = "无效的审核状态",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    class Validator : ConstraintValidator<ValidAuditStatus, Int> {
        override fun isValid(value: Int?, context: ConstraintValidatorContext): Boolean = true
    }
}
