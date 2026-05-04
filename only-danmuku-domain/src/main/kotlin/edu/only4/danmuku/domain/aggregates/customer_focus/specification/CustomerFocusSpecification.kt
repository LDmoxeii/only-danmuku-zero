package edu.only4.danmuku.domain.aggregates.customer_focus.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_focus.CustomerFocus
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerFocus",
    name = "CustomerFocusSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class CustomerFocusSpecification : Specification<CustomerFocus> {

    override fun specify(entity: CustomerFocus): Result {
        return Result.pass()
    }
}
