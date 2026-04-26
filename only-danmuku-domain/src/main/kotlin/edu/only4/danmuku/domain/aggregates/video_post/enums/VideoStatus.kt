package edu.only4.danmuku.domain.aggregates.video_post.enums

import jakarta.persistence.AttributeConverter

enum class VideoStatus(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知状态"),

    TRANSCODING(1, "转码中"),

    TRANSCODE_FAILED(2, "转码失败"),

    PENDING_REVIEW(3, "待审核"),

    REVIEW_PASSED(4, "审核成功"),

    REVIEW_FAILED(5, "审核失败");

    companion object {
        private val enumMap: Map<Int, VideoStatus> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): VideoStatus? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<VideoStatus, Int> {
        override fun convertToDatabaseColumn(attribute: VideoStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): VideoStatus? {
            return valueOfOrNull(dbData)
        }
    }
}
