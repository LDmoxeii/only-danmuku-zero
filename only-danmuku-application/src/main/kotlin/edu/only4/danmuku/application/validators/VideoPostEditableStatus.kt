package edu.only4.danmuku.application.validators

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_draft.GetVideoPostInfoQry
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.util.UUID
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VideoPostEditableStatus.Validator::class])
@MustBeDocumented
annotation class VideoPostEditableStatus(
    val message: String = "视频草稿状态不可编辑",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val videoIdField: String = "videoId",
    val userIdField: String = "customerId",
) {

    class Validator : ConstraintValidator<VideoPostEditableStatus, Any> {
        private lateinit var videoIdProperty: String
        private lateinit var userIdProperty: String

        override fun initialize(constraintAnnotation: VideoPostEditableStatus) {
            videoIdProperty = constraintAnnotation.videoIdField
            userIdProperty = constraintAnnotation.userIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            val (videoId, userId) = extractIds(value) ?: return true

            val resp = runCatching {
                Mediator.queries.send(
                    GetVideoPostInfoQry.Request(
                        videoPostId = videoId,
                        userId = userId
                    )
                )
            }.getOrNull() ?: return false

            val statusEnum = resp.videoInfo.status
            return statusEnum != VideoStatus.REVIEW_PASSED
        }

        private fun extractIds(source: Any?): Pair<UUID, UUID>? {
            if (source == null) return null
            return when (source) {
                is Number -> null // 仅数字无法区分 userId
                is String -> null // 同上
                else -> {
                    val props = source::class.memberProperties.associateBy { it.name }
                    val vRaw = props[videoIdProperty]?.getter?.call(source)
                    val uRaw = props[userIdProperty]?.getter?.call(source)
                    val vId = toUuid(vRaw)
                    val uId = toUuid(uRaw)
                    if (vId != null && uId != null) vId to uId else null
                }
            }
        }

        private fun toUuid(raw: Any?): UUID? = when (raw) {
            is UUID -> raw
            is String -> runCatching { UUID.fromString(raw) }.getOrNull()
            else -> null
        }
    }
}
