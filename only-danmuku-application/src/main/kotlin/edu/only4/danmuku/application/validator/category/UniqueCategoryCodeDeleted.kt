package edu.only4.danmuku.application.validator.category

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.category.UniqueCategoryCodeDeletedQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueCategoryCodeDeleted.Validator::class])
annotation class UniqueCategoryCodeDeleted(
    val message: String = "唯一性校验未通过",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val codeField: String = "code",
    val deletedField: String = "deleted",
    val categoryIdField: String = "categoryId",
) {
    class Validator : ConstraintValidator<UniqueCategoryCodeDeleted, Any> {
        private lateinit var codeProperty: String
        private lateinit var deletedProperty: String
        private lateinit var categoryIdProperty: String

        override fun initialize(constraintAnnotation: UniqueCategoryCodeDeleted) {
            codeProperty = constraintAnnotation.codeField
            deletedProperty = constraintAnnotation.deletedField
            categoryIdProperty = constraintAnnotation.categoryIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) {
                return true
            }

            val result = runCatching {
                val props = value::class.memberProperties.associateBy { it.name }
                val codePropertyRef = props[codeProperty] ?: return false
                val code = codePropertyRef.getter.call(value) as? String
                val codeTrimmed = code?.trim()
                val deletedPropertyRef = props[deletedProperty] ?: return false
                val deleted = deletedPropertyRef.getter.call(value) as? Long
                val excludeId = props[categoryIdProperty]?.getter?.call(value) as? Long

                val allPresent =
                    (codeTrimmed != null && codeTrimmed.isNotBlank()) &&
                    (deleted != null)
                if (!allPresent) {
                    return true
                }

                Mediator.queries.send(
                    UniqueCategoryCodeDeletedQry.Request(
                        code = codeTrimmed!!,
                        deleted = deleted!!,
                        excludeCategoryId = excludeId,
                    )
                )
            }.getOrNull() ?: return false

            return !result.exists
        }
    }
}
