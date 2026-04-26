package edu.only4.danmuku.application.subscribers.domain.video

import edu.only4.danmuku.domain.aggregates.video.events.VideoLikeCountDeltaAppliedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoLikeCountDeltaAppliedDomainEventSubscriber {

    @EventListener(VideoLikeCountDeltaAppliedDomainEvent::class)
    fun on(event: VideoLikeCountDeltaAppliedDomainEvent) {
    }
}
