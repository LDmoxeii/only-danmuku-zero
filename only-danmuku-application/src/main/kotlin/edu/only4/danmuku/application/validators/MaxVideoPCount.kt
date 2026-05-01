package edu.only4.danmuku.application.validators

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.danmuku.application._share.config.properties.SysSettingProperties
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/**
 * 校验上传的视频分片数量是否超过系统设置最大值
 *
 * 默认从请求对象中读取 `pCount` 字段，也可通过注解参数自定义字段名。
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
    val videoIdField: String = "videoId",
) {

    class Validator : ConstraintValidator<MaxVideoPCount, Any> {
        private lateinit var countProperty: String
        private lateinit var videoIdProperty: String

        override fun initialize(constraintAnnotation: MaxVideoPCount) {
            countProperty = constraintAnnotation.countField
            videoIdProperty = constraintAnnotation.videoIdField
        }

        override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
            if (value == null) return true

            val sys = Mediator.ioc.getBean(SysSettingProperties::class.java)
            val maxCount = sys.videoPCount

            var actual = when (value) {
                is Number -> value.toInt()
                is Collection<*> -> value.size
                else -> {
                    val props = value::class.memberProperties.associateBy { it.name }
                    when (val v = props[countProperty]?.getter?.call(value)) {
                        is Number -> v.toInt()
                        is Collection<*> -> v.size
                        else -> 0
                    }
                }
            }
            return actual <= maxCount
        }
    }
}
