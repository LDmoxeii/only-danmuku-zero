package edu.only4.danmuku.application.video_play_history.events

import edu.only4.danmuku.domain.aggregates.video_play_history.events.PlayHistoryAddedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PlayHistoryAddedDomainEventSubscriber {

    @EventListener(PlayHistoryAddedDomainEvent::class)
    fun on(event: PlayHistoryAddedDomainEvent) {
    }
}
