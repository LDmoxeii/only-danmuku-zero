package edu.only4.danmuku.application.video.events

import edu.only4.danmuku.domain.aggregates.video.events.VideoDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoDeletedDomainEventSubscriber {

    @EventListener(VideoDeletedDomainEvent::class)
    fun on(event: VideoDeletedDomainEvent) {
    }
}
