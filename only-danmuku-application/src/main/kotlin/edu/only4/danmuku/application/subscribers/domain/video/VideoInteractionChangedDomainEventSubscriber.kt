package edu.only4.danmuku.application.subscribers.domain.video

import edu.only4.danmuku.domain.aggregates.video.events.VideoInteractionChangedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoInteractionChangedDomainEventSubscriber {

    @EventListener(VideoInteractionChangedDomainEvent::class)
    fun on(event: VideoInteractionChangedDomainEvent) {
    }
}
