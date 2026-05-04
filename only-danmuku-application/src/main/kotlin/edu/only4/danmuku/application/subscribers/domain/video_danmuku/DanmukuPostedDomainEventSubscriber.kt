package edu.only4.danmuku.application.subscribers.domain.video_danmuku

import edu.only4.danmuku.domain.aggregates.video_danmuku.events.DanmukuPostedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class DanmukuPostedDomainEventSubscriber {

    @EventListener(DanmukuPostedDomainEvent::class)
    fun on(event: DanmukuPostedDomainEvent) {
    }
}
