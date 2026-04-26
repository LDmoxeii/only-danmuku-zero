package edu.only4.danmuku.domain.aggregates.user_login_log.enums

import jakarta.persistence.AttributeConverter

enum class LoginResult(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知结果"),

    SUCCESS(1, "成功"),

    FAILURE(2, "失败");

    companion object {
        private val enumMap: Map<Int, LoginResult> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): LoginResult? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<LoginResult, Int> {
        override fun convertToDatabaseColumn(attribute: LoginResult?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): LoginResult? {
            return valueOfOrNull(dbData)
        }
    }
}
