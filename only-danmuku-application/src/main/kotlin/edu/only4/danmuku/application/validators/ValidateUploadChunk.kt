package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 分片上传参数非法
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidateUploadChunk.Validator::class])
@MustBeDocumented
annotation class ValidateUploadChunk(
    val message: String = "分片上传参数非法",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val customerIdField: String = "customerId",
    val uploadIdField: String = "uploadId",
    val chunkIndexField: String = "chunkIndex",
    val chunkFileField: String = "chunkFile",
    val chunkSizeField: String = "chunkSize"
) {
    class Validator : ConstraintValidator<ValidateUploadChunk, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
