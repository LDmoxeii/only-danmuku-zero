package edu.only4.danmuku.application.subscribers.domain.video_play_history

import edu.only4.danmuku.domain.aggregates.video_play_history.events.VideoPlayHistoryCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPlayHistoryCreatedDomainEventSubscriber {

    @EventListener(VideoPlayHistoryCreatedDomainEvent::class)
    fun on(event: VideoPlayHistoryCreatedDomainEvent) {
    }
}
