package edu.only4.danmuku.domain.aggregates.customer_message.enums

import jakarta.persistence.AttributeConverter

enum class MessageType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知消息"),

    SYSTEM_MESSAGE(1, "系统消息"),

    LIKE_MESSAGE(2, "收到的赞"),

    COLLECTION_MESSAGE(3, "收到收藏"),

    COMMENT_MENTION(4, "评论和@"),

    PRIVATE_MESSAGE(5, "私信消息");

    companion object {
        private val enumMap: Map<Int, MessageType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): MessageType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<MessageType, Int> {
        override fun convertToDatabaseColumn(attribute: MessageType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): MessageType? {
            return valueOfOrNull(dbData)
        }
    }
}
