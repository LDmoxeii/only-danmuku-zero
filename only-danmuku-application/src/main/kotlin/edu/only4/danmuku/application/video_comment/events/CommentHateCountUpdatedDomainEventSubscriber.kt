package edu.only4.danmuku.application.video_comment.events

import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentHateCountUpdatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CommentHateCountUpdatedDomainEventSubscriber {

    @EventListener(CommentHateCountUpdatedDomainEvent::class)
    fun on(event: CommentHateCountUpdatedDomainEvent) {
    }
}
