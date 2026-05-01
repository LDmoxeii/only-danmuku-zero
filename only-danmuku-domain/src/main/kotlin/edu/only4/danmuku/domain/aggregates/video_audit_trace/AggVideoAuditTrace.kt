package edu.only4.danmuku.domain.aggregates.video_audit_trace

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_audit_trace.VideoAuditTrace
import edu.only4.danmuku.domain.aggregates.video_audit_trace.factory.VideoAuditTraceFactory

/**
 * VideoAuditTrace aggregate wrapper
 * 视频审核追溯记录
 */
class AggVideoAuditTrace(
    payload: VideoAuditTraceFactory.Payload? = null,
) : Aggregate.Default<VideoAuditTrace>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoAuditTrace, Long>(key)
}
