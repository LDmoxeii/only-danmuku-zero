package edu.only4.danmuku.domain.aggregates.customer_message.enums

import jakarta.persistence.AttributeConverter

enum class ReadType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知状态"),

    UNREAD(1, "未读"),

    READ(2, "已读");

    companion object {
        private val enumMap: Map<Int, ReadType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): ReadType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<ReadType, Int> {
        override fun convertToDatabaseColumn(attribute: ReadType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): ReadType? {
            return valueOfOrNull(dbData)
        }
    }
}
