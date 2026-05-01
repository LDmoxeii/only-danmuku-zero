package edu.only4.danmuku.application.video_play_history.events

import edu.only4.danmuku.domain.aggregates.video_play_history.events.PlayProgressUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class PlayProgressUpdatedDomainEventSubscriber {

    @EventListener(PlayProgressUpdatedDomainEvent::class)
    fun on(event: PlayProgressUpdatedDomainEvent) {
    }
}
