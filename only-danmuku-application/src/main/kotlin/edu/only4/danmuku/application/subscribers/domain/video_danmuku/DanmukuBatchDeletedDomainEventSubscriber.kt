package edu.only4.danmuku.application.subscribers.domain.video_danmuku

import edu.only4.danmuku.domain.aggregates.video_danmuku.events.DanmukuBatchDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class DanmukuBatchDeletedDomainEventSubscriber {

    @EventListener(DanmukuBatchDeletedDomainEvent::class)
    fun on(event: DanmukuBatchDeletedDomainEvent) {
    }
}
