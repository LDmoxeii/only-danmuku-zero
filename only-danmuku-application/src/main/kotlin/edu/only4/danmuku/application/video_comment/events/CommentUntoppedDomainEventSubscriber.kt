package edu.only4.danmuku.application.video_comment.events

import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentUntoppedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CommentUntoppedDomainEventSubscriber {

    @EventListener(CommentUntoppedDomainEvent::class)
    fun on(event: CommentUntoppedDomainEvent) {
    }
}
