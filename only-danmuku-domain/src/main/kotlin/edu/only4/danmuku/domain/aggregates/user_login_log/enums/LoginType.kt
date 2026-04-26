package edu.only4.danmuku.domain.aggregates.user_login_log.enums

import jakarta.persistence.AttributeConverter

enum class LoginType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知登录类型"),

    PASSWORD(1, "密码登录"),

    SMS_CODE(2, "短信验证码登录"),

    LOGOUT(3, "退出登录");

    companion object {
        private val enumMap: Map<Int, LoginType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): LoginType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<LoginType, Int> {
        override fun convertToDatabaseColumn(attribute: LoginType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): LoginType? {
            return valueOfOrNull(dbData)
        }
    }
}
