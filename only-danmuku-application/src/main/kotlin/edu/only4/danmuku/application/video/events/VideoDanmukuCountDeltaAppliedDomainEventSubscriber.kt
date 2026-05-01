package edu.only4.danmuku.application.video.events

import edu.only4.danmuku.domain.aggregates.video.events.VideoDanmukuCountDeltaAppliedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoDanmukuCountDeltaAppliedDomainEventSubscriber {

    @EventListener(VideoDanmukuCountDeltaAppliedDomainEvent::class)
    fun on(event: VideoDanmukuCountDeltaAppliedDomainEvent) {
    }
}
