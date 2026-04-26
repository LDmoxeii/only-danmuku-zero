package edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums

import jakarta.persistence.AttributeConverter

enum class EncryptTokenStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    VALID(1, "有效"),

    EXHAUSTED(2, "已用尽"),

    EXPIRED(3, "过期"),

    REVOKED(4, "吊销");

    companion object {
        private val enumMap: Map<Int, EncryptTokenStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): EncryptTokenStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<EncryptTokenStatus, Int> {
        override fun convertToDatabaseColumn(attribute: EncryptTokenStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): EncryptTokenStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
