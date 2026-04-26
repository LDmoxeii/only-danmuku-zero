package edu.only4.danmuku.application.subscribers.domain.video

import edu.only4.danmuku.domain.aggregates.video.events.VideoPlayCountIncreasedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPlayCountIncreasedDomainEventSubscriber {

    @EventListener(VideoPlayCountIncreasedDomainEvent::class)
    fun on(event: VideoPlayCountIncreasedDomainEvent) {
    }
}
