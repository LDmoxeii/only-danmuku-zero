package edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums

import jakarta.persistence.AttributeConverter

enum class EncryptKeyStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    ACTIVE(1, "可用"),

    REVOKED(2, "吊销"),

    EXPIRED(3, "过期");

    companion object {
        private val enumMap: Map<Int, EncryptKeyStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): EncryptKeyStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<EncryptKeyStatus, Int> {
        override fun convertToDatabaseColumn(attribute: EncryptKeyStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): EncryptKeyStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
