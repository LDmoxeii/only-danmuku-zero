package edu.only4.danmuku.application.video_post_processing.events

import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingStartedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPostProcessingStartedDomainEventSubscriber {

    @EventListener(VideoPostProcessingStartedDomainEvent::class)
    fun on(event: VideoPostProcessingStartedDomainEvent) {
    }
}
