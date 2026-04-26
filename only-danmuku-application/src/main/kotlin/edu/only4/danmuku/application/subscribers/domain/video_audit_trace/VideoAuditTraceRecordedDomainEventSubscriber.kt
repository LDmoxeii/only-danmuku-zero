package edu.only4.danmuku.application.subscribers.domain.video_audit_trace

import edu.only4.danmuku.domain.aggregates.video_audit_trace.events.VideoAuditTraceRecordedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoAuditTraceRecordedDomainEventSubscriber {

    @EventListener(VideoAuditTraceRecordedDomainEvent::class)
    fun on(event: VideoAuditTraceRecordedDomainEvent) {
    }
}
