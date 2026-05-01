package edu.only4.danmuku.domain.aggregates.category

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.category.Category
import edu.only4.danmuku.domain.aggregates.category.factory.CategoryFactory

/**
 * Category aggregate wrapper
 * 分类信息
 */
class AggCategory(
    payload: CategoryFactory.Payload? = null,
) : Aggregate.Default<Category>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggCategory, Long>(key)
}
