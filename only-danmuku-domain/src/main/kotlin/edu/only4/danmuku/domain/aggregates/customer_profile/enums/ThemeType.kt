package edu.only4.danmuku.domain.aggregates.customer_profile.enums

import jakarta.persistence.AttributeConverter

enum class ThemeType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知主题"),

    LIGHT(1, "浅色主题"),

    DARK(2, "深色主题"),

    SYSTEM(3, "跟随系统");

    companion object {
        private val enumMap: Map<Int, ThemeType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): ThemeType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<ThemeType, Int> {
        override fun convertToDatabaseColumn(attribute: ThemeType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): ThemeType? {
            return valueOfOrNull(dbData)
        }
    }
}
