package edu.only4.danmuku.application.subscribers.domain.video_comment

import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentToppedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class CommentToppedDomainEventSubscriber {

    @EventListener(CommentToppedDomainEvent::class)
    fun on(event: CommentToppedDomainEvent) {
    }
}
