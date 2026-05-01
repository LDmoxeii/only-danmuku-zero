package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 视频弹幕功能必须开启
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DanmukuInteractionAllowed.Validator::class])
@MustBeDocumented
annotation class DanmukuInteractionAllowed(
    val message: String = "校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    class Validator : ConstraintValidator<DanmukuInteractionAllowed, Long> {
        override fun isValid(value: Long?, context: ConstraintValidatorContext): Boolean = true
    }
}
