package edu.only4.danmuku.application.validators

import java.util.UUID

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video.GetVideoInfoQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 验证视频评论区未关闭
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CommentNotClosed.Validator::class])
@MustBeDocumented
annotation class CommentNotClosed(
    val message: String = "UP主已关闭评论区",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
) {
    class Validator : ConstraintValidator<CommentNotClosed, UUID> {
        override fun isValid(value: UUID?, context: ConstraintValidatorContext): Boolean {
            val videoId = value ?: return true
            val response = runCatching {
                Mediator.queries.send(GetVideoInfoQry.Request(videoId))
            }.getOrNull() ?: return true

            val interaction = response.interaction ?: return true
            // 互动设置包含 "0" 表示评论关闭
            return !interaction.contains("0")
        }
    }
}


