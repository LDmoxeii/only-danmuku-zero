package edu.only4.danmuku.application.video_post.events

import edu.only4.danmuku.domain.aggregates.video_post.events.VideoDraftSubmittedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoDraftSubmittedDomainEventSubscriber {

    @EventListener(VideoDraftSubmittedDomainEvent::class)
    fun on(event: VideoDraftSubmittedDomainEvent) {
    }
}
