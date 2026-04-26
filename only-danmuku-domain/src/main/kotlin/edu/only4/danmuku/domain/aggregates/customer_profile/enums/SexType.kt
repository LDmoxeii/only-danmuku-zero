package edu.only4.danmuku.domain.aggregates.customer_profile.enums

import jakarta.persistence.AttributeConverter

enum class SexType(
    val value: Int,
    val description: String
) {

    UNKNOWN(0, "未知"),

    FEMALE(1, "女"),

    MALE(2, "男");

    companion object {
        private val enumMap: Map<Int, SexType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): SexType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<SexType, Int> {
        override fun convertToDatabaseColumn(attribute: SexType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): SexType? {
            return valueOfOrNull(dbData)
        }
    }
}
