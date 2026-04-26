package edu.only4.danmuku.application.subscribers.domain.user

import edu.only4.danmuku.domain.aggregates.user.events.RelationshipBoundDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class RelationshipBoundDomainEventSubscriber {

    @EventListener(RelationshipBoundDomainEvent::class)
    fun on(event: RelationshipBoundDomainEvent) {
    }
}
