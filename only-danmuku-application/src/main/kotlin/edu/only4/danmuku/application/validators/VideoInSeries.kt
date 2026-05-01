package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 视频不在指定系列中
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoInSeries.Validator::class])
@MustBeDocumented
annotation class VideoInSeries(
    val message: String = "视频不在指定系列中",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val seriesIdField: String = "seriesId",
    val videoIdField: String = "videoId"
) {
    class Validator : ConstraintValidator<VideoInSeries, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
