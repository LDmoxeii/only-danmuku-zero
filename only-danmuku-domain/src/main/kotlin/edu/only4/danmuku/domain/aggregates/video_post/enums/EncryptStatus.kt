package edu.only4.danmuku.domain.aggregates.video_post.enums

import jakarta.persistence.AttributeConverter

enum class EncryptStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    UNENCRYPTED(1, "未加密"),

    ENCRYPTING(2, "加密中"),

    ENCRYPTED(3, "已加密"),

    FAILED(4, "失败");

    companion object {
        private val enumMap: Map<Int, EncryptStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): EncryptStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<EncryptStatus, Int> {
        override fun convertToDatabaseColumn(attribute: EncryptStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): EncryptStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
