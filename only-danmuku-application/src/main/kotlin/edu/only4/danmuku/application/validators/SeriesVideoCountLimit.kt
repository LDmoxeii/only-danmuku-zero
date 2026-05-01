package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 系列视频数量超过允许上限
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SeriesVideoCountLimit.Validator::class])
@MustBeDocumented
annotation class SeriesVideoCountLimit(
    val message: String = "系列视频数量超过允许上限",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdsField: String = "videoIds"
) {
    class Validator : ConstraintValidator<SeriesVideoCountLimit, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
