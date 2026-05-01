package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 无权置顶该评论
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoCommentOwner.Validator::class])
@MustBeDocumented
annotation class VideoCommentOwner(
    val message: String = "无权置顶该评论",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val commentIdField: String = "commentId",
    val operatorIdField: String = "operatorId"
) {
    class Validator : ConstraintValidator<VideoCommentOwner, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
