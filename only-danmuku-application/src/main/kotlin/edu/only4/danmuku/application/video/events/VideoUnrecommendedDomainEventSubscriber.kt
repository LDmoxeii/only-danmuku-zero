package edu.only4.danmuku.application.video.events

import edu.only4.danmuku.domain.aggregates.video.events.VideoUnrecommendedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoUnrecommendedDomainEventSubscriber {

    @EventListener(VideoUnrecommendedDomainEvent::class)
    fun on(event: VideoUnrecommendedDomainEvent) {
    }
}
