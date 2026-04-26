package edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums

import jakarta.persistence.AttributeConverter

enum class AbnormalOpType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知异常"),

    PASSWORD_FAIL_TOO_MANY_TIMES(1, "密码失败次数过多");

    companion object {
        private val enumMap: Map<Int, AbnormalOpType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): AbnormalOpType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<AbnormalOpType, Int> {
        override fun convertToDatabaseColumn(attribute: AbnormalOpType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): AbnormalOpType? {
            return valueOfOrNull(dbData)
        }
    }
}
