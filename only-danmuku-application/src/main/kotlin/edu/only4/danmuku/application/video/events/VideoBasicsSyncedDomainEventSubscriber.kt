package edu.only4.danmuku.application.video.events

import edu.only4.danmuku.domain.aggregates.video.events.VideoBasicsSyncedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoBasicsSyncedDomainEventSubscriber {

    @EventListener(VideoBasicsSyncedDomainEvent::class)
    fun on(event: VideoBasicsSyncedDomainEvent) {
    }
}
