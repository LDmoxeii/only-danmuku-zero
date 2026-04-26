package edu.only4.danmuku.domain.aggregates.video_post.enums

import jakarta.persistence.AttributeConverter

enum class EncryptMethod(
    val value: Int,
    val description: String
) {

    HLS_AES_128(1, "AES-128"),

    SAMPLE_AES(2, "SAMPLE-AES"),

    DRM(3, "DRM占位");

    companion object {
        private val enumMap: Map<Int, EncryptMethod> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): EncryptMethod? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<EncryptMethod, Int> {
        override fun convertToDatabaseColumn(attribute: EncryptMethod?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): EncryptMethod? {
            return valueOfOrNull(dbData)
        }
    }
}
