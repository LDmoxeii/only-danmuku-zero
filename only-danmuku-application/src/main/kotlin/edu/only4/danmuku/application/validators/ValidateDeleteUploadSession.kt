package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 删除上传会话参数非法
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidateDeleteUploadSession.Validator::class])
@MustBeDocumented
annotation class ValidateDeleteUploadSession(
    val message: String = "删除上传会话参数非法",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val customerIdField: String = "customerId",
    val uploadIdField: String = "uploadId"
) {
    class Validator : ConstraintValidator<ValidateDeleteUploadSession, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
