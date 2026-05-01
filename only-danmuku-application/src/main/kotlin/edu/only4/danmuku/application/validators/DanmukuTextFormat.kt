package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 弹幕参数格式不正确
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DanmukuTextFormat.Validator::class])
@MustBeDocumented
annotation class DanmukuTextFormat(
    val message: String = "弹幕参数格式不正确",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val textField: String = "text",
    val colorField: String = "color",
    val modeField: String = "mode",
    val timeField: String = "time"
) {
    class Validator : ConstraintValidator<DanmukuTextFormat, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
