package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 视频草稿状态不可编辑
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoPostEditableStatus.Validator::class])
@MustBeDocumented
annotation class VideoPostEditableStatus(
    val message: String = "视频草稿状态不可编辑",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val userIdField: String = "customerId"
) {
    class Validator : ConstraintValidator<VideoPostEditableStatus, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
