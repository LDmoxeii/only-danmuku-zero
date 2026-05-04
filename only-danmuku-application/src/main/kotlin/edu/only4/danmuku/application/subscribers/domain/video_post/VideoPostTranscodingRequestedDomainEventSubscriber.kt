package edu.only4.danmuku.application.subscribers.domain.video_post

import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostTranscodingRequestedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPostTranscodingRequestedDomainEventSubscriber {

    @EventListener(VideoPostTranscodingRequestedDomainEvent::class)
    fun on(event: VideoPostTranscodingRequestedDomainEvent) {
    }
}
