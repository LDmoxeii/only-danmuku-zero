package edu.only4.danmuku.application.subscribers.domain.video_danmuku

import edu.only4.danmuku.domain.aggregates.video_danmuku.events.DanmukuDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class DanmukuDeletedDomainEventSubscriber {

    @EventListener(DanmukuDeletedDomainEvent::class)
    fun on(event: DanmukuDeletedDomainEvent) {
    }
}
