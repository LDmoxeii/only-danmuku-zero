package edu.only4.danmuku.application.validator.video_hls_key_token

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_hls_key_token.UniqueVideoHlsKeyTokenTokenHashDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueVideoHlsKeyTokenTokenHashDeleted.Validator::class])
annotation class UniqueVideoHlsKeyTokenTokenHashDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val tokenHashField: String = "tokenHash",
    val deletedField: String = "deleted",
    val videoHlsKeyTokenIdField: String = "videoHlsKeyTokenId",
) {
    class Validator : ConstraintValidator<UniqueVideoHlsKeyTokenTokenHashDeleted, Any> {
        private lateinit var tokenHashProperty: String
        private lateinit var deletedProperty: String
        private lateinit var videoHlsKeyTokenIdProperty: String

        override fun initialize(constraintAnnotation: UniqueVideoHlsKeyTokenTokenHashDeleted) {
            tokenHashProperty = constraintAnnotation.tokenHashField
            deletedProperty = constraintAnnotation.deletedField
            videoHlsKeyTokenIdProperty = constraintAnnotation.videoHlsKeyTokenIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val tokenHashPropertyRef = props[tokenHashProperty] ?: return false
                val tokenHash = tokenHashPropertyRef.getter.call(value) as? String
                val tokenHashTrimmed = tokenHash?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[videoHlsKeyTokenIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (tokenHashTrimmed != null && tokenHashTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueVideoHlsKeyTokenTokenHashDeletedQry.Request(
                        tokenHash = tokenHashTrimmed!!,
                        deleted = deleted!!,
                        excludeVideoHlsKeyTokenId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
