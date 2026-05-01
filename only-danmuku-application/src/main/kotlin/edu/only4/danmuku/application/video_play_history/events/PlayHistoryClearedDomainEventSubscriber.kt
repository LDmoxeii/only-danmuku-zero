package edu.only4.danmuku.application.video_play_history.events

import edu.only4.danmuku.domain.aggregates.video_play_history.events.PlayHistoryClearedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PlayHistoryClearedDomainEventSubscriber {

    @EventListener(PlayHistoryClearedDomainEvent::class)
    fun on(event: PlayHistoryClearedDomainEvent) {
    }
}
