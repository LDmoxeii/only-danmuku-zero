package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 视频草稿不存在
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoPostExists.Validator::class])
@MustBeDocumented
annotation class VideoPostExists(
    val message: String = "视频草稿不存在",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val userIdField: String = "customerId"
) {
    class Validator : ConstraintValidator<VideoPostExists, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
