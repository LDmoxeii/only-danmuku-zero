package edu.only4.danmuku.application.video_post.events

import edu.only4.danmuku.domain.aggregates.video_post.events.VideoFileDraftTranscodedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoFileDraftTranscodedDomainEventSubscriber {

    @EventListener(VideoFileDraftTranscodedDomainEvent::class)
    fun on(event: VideoFileDraftTranscodedDomainEvent) {
    }
}
