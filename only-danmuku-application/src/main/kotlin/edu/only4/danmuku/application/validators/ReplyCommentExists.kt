package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 被回复的评论不存在或不属于该视频
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ReplyCommentExists.Validator::class])
@MustBeDocumented
annotation class ReplyCommentExists(
    val message: String = "被回复的评论不存在或不属于该视频",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val replyCommentIdField: String = "replyCommentId"
) {
    class Validator : ConstraintValidator<ReplyCommentExists, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
