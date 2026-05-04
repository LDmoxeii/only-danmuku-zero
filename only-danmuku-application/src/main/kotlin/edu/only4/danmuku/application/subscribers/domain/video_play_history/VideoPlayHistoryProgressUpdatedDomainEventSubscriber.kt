package edu.only4.danmuku.application.subscribers.domain.video_play_history

import edu.only4.danmuku.domain.aggregates.video_play_history.events.VideoPlayHistoryProgressUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoPlayHistoryProgressUpdatedDomainEventSubscriber {

    @EventListener(VideoPlayHistoryProgressUpdatedDomainEvent::class)
    fun on(event: VideoPlayHistoryProgressUpdatedDomainEvent) {
    }
}
