package edu.only4.danmuku.domain.aggregates.video_audit_trace.enums

import jakarta.persistence.AttributeConverter

enum class AuditStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知"),

    PASSED(1, "审核通过"),

    FAILED(2, "审核不通过");

    companion object {
        private val enumMap: Map<Int, AuditStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): AuditStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<AuditStatus, Int> {
        override fun convertToDatabaseColumn(attribute: AuditStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): AuditStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
