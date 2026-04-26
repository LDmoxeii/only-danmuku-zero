package edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums

import jakarta.persistence.AttributeConverter

enum class UploadStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知类型"),

    CREATED(1, "已创建"),

    UPLOADING(2, "上传中"),

    DONE(3, "完成"),

    ABORTED(4, "已放弃"),

    EXPIRED(5, "已过期");

    companion object {
        private val enumMap: Map<Int, UploadStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): UploadStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<UploadStatus, Int> {
        override fun convertToDatabaseColumn(attribute: UploadStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): UploadStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
