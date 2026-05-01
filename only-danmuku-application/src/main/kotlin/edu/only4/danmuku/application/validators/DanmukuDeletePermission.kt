package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 无权限删除该弹幕
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DanmukuDeletePermission.Validator::class])
@MustBeDocumented
annotation class DanmukuDeletePermission(
    val message: String = "无权限删除该弹幕",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val danmukuIdField: String = "danmukuId",
    val operatorIdField: String = "operatorId"
) {
    class Validator : ConstraintValidator<DanmukuDeletePermission, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
