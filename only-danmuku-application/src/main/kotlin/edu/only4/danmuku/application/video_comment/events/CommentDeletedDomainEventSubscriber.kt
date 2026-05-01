package edu.only4.danmuku.application.video_comment.events

import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentDeletedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CommentDeletedDomainEventSubscriber {

    @EventListener(CommentDeletedDomainEvent::class)
    fun on(event: CommentDeletedDomainEvent) {
    }
}
