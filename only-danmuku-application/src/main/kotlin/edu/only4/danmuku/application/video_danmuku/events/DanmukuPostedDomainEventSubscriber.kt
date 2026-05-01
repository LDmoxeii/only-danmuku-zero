package edu.only4.danmuku.application.video_danmuku.events

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
