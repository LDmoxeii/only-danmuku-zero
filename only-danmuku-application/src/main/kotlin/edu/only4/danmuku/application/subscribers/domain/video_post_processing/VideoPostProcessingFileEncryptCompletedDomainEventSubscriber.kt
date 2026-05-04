package edu.only4.danmuku.application.subscribers.domain.video_post_processing

import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingFileEncryptCompletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPostProcessingFileEncryptCompletedDomainEventSubscriber {

    @EventListener(VideoPostProcessingFileEncryptCompletedDomainEvent::class)
    fun on(event: VideoPostProcessingFileEncryptCompletedDomainEvent) {
    }
}
