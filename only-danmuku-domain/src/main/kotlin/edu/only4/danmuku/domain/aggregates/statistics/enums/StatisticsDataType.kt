package edu.only4.danmuku.domain.aggregates.statistics.enums

import jakarta.persistence.AttributeConverter

enum class StatisticsDataType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知类型"),

    PLAY(1, "播放量"),

    FANS(2, "粉丝"),

    LIKE(3, "点赞"),

    COLLECTION(4, "收藏"),

    COIN(5, "投币"),

    COMMENT(6, "评论"),

    DANMUKU(7, "弹幕");

    companion object {
        private val enumMap: Map<Int, StatisticsDataType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): StatisticsDataType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<StatisticsDataType, Int> {
        override fun convertToDatabaseColumn(attribute: StatisticsDataType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): StatisticsDataType? {
            return valueOfOrNull(dbData)
        }
    }
}
