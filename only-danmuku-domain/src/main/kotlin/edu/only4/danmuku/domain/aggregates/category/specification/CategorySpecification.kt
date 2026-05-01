package edu.only4.danmuku.domain.aggregates.category.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.category.Category
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "Category",
    name = "CategorySpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class CategorySpecification : Specification<Category> {

    override fun specify(entity: Category): Result {
        return Result.pass()
    }
}
