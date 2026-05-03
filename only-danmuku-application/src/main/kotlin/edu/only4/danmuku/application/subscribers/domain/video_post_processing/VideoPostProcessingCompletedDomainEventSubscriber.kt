package edu.only4.danmuku.application.subscribers.domain.video_post_processing

import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingCompletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPostProcessingCompletedDomainEventSubscriber {

    @EventListener(VideoPostProcessingCompletedDomainEvent::class)
    fun on(event: VideoPostProcessingCompletedDomainEvent) {
    }
}
