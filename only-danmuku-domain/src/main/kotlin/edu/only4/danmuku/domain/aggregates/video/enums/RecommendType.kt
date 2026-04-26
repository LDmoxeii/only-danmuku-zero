package edu.only4.danmuku.domain.aggregates.video.enums

import jakarta.persistence.AttributeConverter

enum class RecommendType(
    val value: Int,
    val description: String
) {

    UNKNOW(0, "未知状态"),

    NOT_RECOMMEND(1, "未推荐"),

    RECOMMEND(2, "已推荐");

    companion object {
        private val enumMap: Map<Int, RecommendType> = entries.associateBy { it.value }

        fun valueOfOrNull(value: Int?): RecommendType? = enumMap[value]
    }

    @jakarta.persistence.Converter(autoApply = false)
    class Converter : AttributeConverter<RecommendType, Int> {
        override fun convertToDatabaseColumn(attribute: RecommendType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): RecommendType? {
            return valueOfOrNull(dbData)
        }
    }
}
