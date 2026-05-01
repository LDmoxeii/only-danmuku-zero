package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 非法的文件路径，存在安全风险
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SafeFilePath.Validator::class])
@MustBeDocumented
annotation class SafeFilePath(
    val message: String = "非法的文件路径，存在安全风险",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    class Validator : ConstraintValidator<SafeFilePath, String> {
        override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean = true
    }
}
