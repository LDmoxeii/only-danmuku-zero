package edu.only4.danmuku.domain.aggregates.video_quality_policy.enums

import jakarta.persistence.AttributeConverter

enum class QualityAuthPolicy(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    PUBLIC(1, "公开"),

    LOGIN(2, "登录"),

    PAID(3, "付费"),

    CUSTOM(4, "自定义");

    companion object {
        private val enumMap: Map<Int, QualityAuthPolicy> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): QualityAuthPolicy? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<QualityAuthPolicy, Int> {
        override fun convertToDatabaseColumn(attribute: QualityAuthPolicy?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): QualityAuthPolicy? {
            return valueOfOrNull(dbData)
        }
    }
}
