package edu.only4.danmuku.domain.aggregates.video_audit_trace.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_audit_trace.VideoAuditTrace
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoAuditTrace",
    name = "VideoAuditTraceSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoAuditTraceSpecification : Specification<VideoAuditTrace> {

    override fun specify(entity: VideoAuditTrace): Result {
        return Result.pass()
    }
}
