package edu.only4.danmuku.application.video.events

import edu.only4.danmuku.domain.aggregates.video.events.VideoLastPlayTimeAttachedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoLastPlayTimeAttachedDomainEventSubscriber {

    @EventListener(VideoLastPlayTimeAttachedDomainEvent::class)
    fun on(event: VideoLastPlayTimeAttachedDomainEvent) {
    }
}
