package edu.only4.danmuku.application.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * 校验分类可删除
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CategoryDeletionAllowed.Validator::class])
@MustBeDocumented
annotation class CategoryDeletionAllowed(
    val message: String = "校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    class Validator : ConstraintValidator<CategoryDeletionAllowed, Long> {
        override fun isValid(value: Long?, context: ConstraintValidatorContext): Boolean = true
    }
}
