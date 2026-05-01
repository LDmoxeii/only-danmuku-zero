package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 无权操作该视频系列
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SeriesOwnership.Validator::class])
@MustBeDocumented
annotation class SeriesOwnership(
    val message: String = "无权操作该视频系列",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val seriesIdField: String = "seriesId",
    val operatorIdField: String = "operatorId"
) {
    class Validator : ConstraintValidator<SeriesOwnership, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
