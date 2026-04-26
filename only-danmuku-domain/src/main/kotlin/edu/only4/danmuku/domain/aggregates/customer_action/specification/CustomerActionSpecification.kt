package edu.only4.danmuku.domain.aggregates.customer_action.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_action.CustomerAction
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerAction",
    name = "CustomerActionSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class CustomerActionSpecification : Specification<CustomerAction> {

    override fun specify(entity: CustomerAction): Result {
        return Result.pass()
    }
}
