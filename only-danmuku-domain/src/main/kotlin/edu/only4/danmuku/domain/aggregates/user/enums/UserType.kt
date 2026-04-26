package edu.only4.danmuku.domain.aggregates.user.enums

import jakarta.persistence.AttributeConverter

enum class UserType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知类型"),

    SYS_USER(1, "系统管理员");

    companion object {
        private val enumMap: Map<Int, UserType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): UserType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<UserType, Int> {
        override fun convertToDatabaseColumn(attribute: UserType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): UserType? {
            return valueOfOrNull(dbData)
        }
    }
}
