package edu.only4.danmuku.domain.aggregates.video_post_processing.enums

import jakarta.persistence.AttributeConverter

enum class ProcessStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    PENDING(1, "待处理"),

    PROCESSING(2, "处理中"),

    SUCCESS(3, "成功"),

    FAILED(4, "失败"),

    SKIPPED(5, "跳过");

    companion object {
        private val enumMap: Map<Int, ProcessStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): ProcessStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<ProcessStatus, Int> {
        override fun convertToDatabaseColumn(attribute: ProcessStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): ProcessStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
