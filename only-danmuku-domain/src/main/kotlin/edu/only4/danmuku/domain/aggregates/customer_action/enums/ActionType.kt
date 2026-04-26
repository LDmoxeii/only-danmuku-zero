package edu.only4.danmuku.domain.aggregates.customer_action.enums

import jakarta.persistence.AttributeConverter

enum class ActionType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知行为"),

    LIKE_COMMENT(1, "评论喜欢点赞"),

    HATE_COMMENT(2, "讨厌评论"),

    LIKE_VIDEO(3, "视频点赞"),

    FAVORITE_VIDEO(4, "视频收藏"),

    COIN_VIDEO(5, "视频投币");

    companion object {
        private val enumMap: Map<Int, ActionType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): ActionType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<ActionType, Int> {
        override fun convertToDatabaseColumn(attribute: ActionType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): ActionType? {
            return valueOfOrNull(dbData)
        }
    }
}
