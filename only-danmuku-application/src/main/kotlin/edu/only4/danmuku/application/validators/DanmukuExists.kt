package edu.only4.danmuku.application.validators

import java.util.UUID

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application.queries.video_danmuku.CheckDanmukuExistsQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

/** 校验弹幕是否存在（通过查询层） */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DanmukuExists.Validator::class])
@MustBeDocumented
annotation class DanmukuExists(
    val message: String = "弹幕不存在",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
) {
    class Validator : ConstraintValidator<DanmukuExists, UUID> {
        override fun isValid(value: UUID?, context: ConstraintValidatorContext): Boolean {
            val danmukuId = value ?: return true
            val resp = runCatching {
                Mediator.queries.send(CheckDanmukuExistsQry.Request(danmukuId = danmukuId))
            }.getOrNull() ?: return false
            return resp.exists
        }
    }
}

