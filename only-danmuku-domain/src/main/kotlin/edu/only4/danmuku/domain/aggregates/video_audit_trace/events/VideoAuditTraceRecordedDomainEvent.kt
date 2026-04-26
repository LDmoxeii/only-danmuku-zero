package edu.only4.danmuku.domain.aggregates.video_audit_trace.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_audit_trace.VideoAuditTrace

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoAuditTrace",
    name = "VideoAuditTraceRecordedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoAuditTraceRecordedDomainEvent(
    val entity: VideoAuditTrace,
    val entity: VideoAuditTrace
) {
}
