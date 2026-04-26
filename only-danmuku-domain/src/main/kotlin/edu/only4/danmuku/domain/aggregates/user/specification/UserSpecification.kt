package edu.only4.danmuku.domain.aggregates.user.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.user.User
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "User",
    name = "UserSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class UserSpecification : Specification<User> {

    override fun specify(entity: User): Result {
        return Result.pass()
    }
}
