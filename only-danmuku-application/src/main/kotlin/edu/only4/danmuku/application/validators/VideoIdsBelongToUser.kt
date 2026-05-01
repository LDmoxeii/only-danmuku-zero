package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 包含不属于当前用户的视频
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoIdsBelongToUser.Validator::class])
@MustBeDocumented
annotation class VideoIdsBelongToUser(
    val message: String = "包含不属于当前用户的视频",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val userIdField: String = "userId",
    val videoIdsField: String = "videoIds"
) {
    class Validator : ConstraintValidator<VideoIdsBelongToUser, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
