package edu.only4.danmuku.domain.aggregates.customer_profile.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "CustomerProfile",
    name = "CustomerProfileSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class CustomerProfileSpecification : Specification<CustomerProfile> {

    override fun specify(entity: CustomerProfile): Result {
        return Result.pass()
    }
}
