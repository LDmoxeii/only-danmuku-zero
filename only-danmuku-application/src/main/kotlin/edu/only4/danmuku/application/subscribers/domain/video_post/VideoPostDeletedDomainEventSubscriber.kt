package edu.only4.danmuku.application.subscribers.domain.video_post

import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPostDeletedDomainEventSubscriber {

    @EventListener(VideoPostDeletedDomainEvent::class)
    fun on(event: VideoPostDeletedDomainEvent) {
    }
}
