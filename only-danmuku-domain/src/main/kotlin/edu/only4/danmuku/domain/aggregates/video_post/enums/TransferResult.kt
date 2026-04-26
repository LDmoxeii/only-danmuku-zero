package edu.only4.danmuku.domain.aggregates.video_post.enums

import jakarta.persistence.AttributeConverter

enum class TransferResult(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知结果"),

    TRANSCODING(1, "转码中"),

    SUCCESS(2, "转码成功"),

    FAILED(3, "转码失败");

    companion object {
        private val enumMap: Map<Int, TransferResult> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): TransferResult? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<TransferResult, Int> {
        override fun convertToDatabaseColumn(attribute: TransferResult?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): TransferResult? {
            return valueOfOrNull(dbData)
        }
    }
}
