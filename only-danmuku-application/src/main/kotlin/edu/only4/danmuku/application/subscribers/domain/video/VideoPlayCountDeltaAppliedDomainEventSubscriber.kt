package edu.only4.danmuku.application.subscribers.domain.video

import edu.only4.danmuku.domain.aggregates.video.events.VideoPlayCountDeltaAppliedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPlayCountDeltaAppliedDomainEventSubscriber {

    @EventListener(VideoPlayCountDeltaAppliedDomainEvent::class)
    fun on(event: VideoPlayCountDeltaAppliedDomainEvent) {
    }
}
