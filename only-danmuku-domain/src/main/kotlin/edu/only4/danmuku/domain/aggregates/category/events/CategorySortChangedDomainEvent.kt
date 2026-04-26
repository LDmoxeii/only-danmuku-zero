package edu.only4.danmuku.domain.aggregates.category.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.category.Category

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "Category",
    name = "CategorySortChangedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class CategorySortChangedDomainEvent(
    val entity: Category,
    val entity: Category
) {
}
