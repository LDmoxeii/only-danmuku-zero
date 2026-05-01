package edu.only4.danmuku.domain.aggregates.category.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.category.Category
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "Category",
    name = "CategoryFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CategoryFactory : AggregateFactory<CategoryFactory.Payload, Category> {

    override fun create(payload: Payload): Category {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "Category",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<Category>
}
