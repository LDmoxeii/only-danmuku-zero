package edu.only4.danmuku.application.subscribers.domain.video

import edu.only4.danmuku.domain.aggregates.video.events.VideoCollectCountDeltaAppliedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoCollectCountDeltaAppliedDomainEventSubscriber {

    @EventListener(VideoCollectCountDeltaAppliedDomainEvent::class)
    fun on(event: VideoCollectCountDeltaAppliedDomainEvent) {
    }
}
