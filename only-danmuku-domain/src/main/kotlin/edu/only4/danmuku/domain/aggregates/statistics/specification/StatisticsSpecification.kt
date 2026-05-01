package edu.only4.danmuku.domain.aggregates.statistics.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.statistics.Statistics
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "Statistics",
    name = "StatisticsSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class StatisticsSpecification : Specification<Statistics> {

    override fun specify(entity: Statistics): Result {
        return Result.pass()
    }
}
