package edu.only4.danmuku.domain.aggregates.video_post.enums

import jakarta.persistence.AttributeConverter

enum class PostType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知类型"),

    ORIGINAL(1, "自制作"),

    REPOST(2, "转载");

    companion object {
        private val enumMap: Map<Int, PostType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): PostType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<PostType, Int> {
        override fun convertToDatabaseColumn(attribute: PostType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): PostType? {
            return valueOfOrNull(dbData)
        }
    }
}
