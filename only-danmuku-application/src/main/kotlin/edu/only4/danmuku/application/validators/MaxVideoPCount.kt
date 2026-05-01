package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 上传分片数量超过系统限制
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxVideoPCount.Validator::class])
@MustBeDocumented
annotation class MaxVideoPCount(
    val message: String = "上传分片数量超过系统限制",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val countField: String = "pCount",
    val videoIdField: String = "videoId"
) {
    class Validator : ConstraintValidator<MaxVideoPCount, Any> {
        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean = true
    }
}
