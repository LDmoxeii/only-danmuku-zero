package edu.only4.danmuku.application.user.events

import edu.only4.danmuku.domain.aggregates.user.events.AccountDisabledDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class AccountDisabledDomainEventSubscriber {

    @EventListener(AccountDisabledDomainEvent::class)
    fun on(event: AccountDisabledDomainEvent) {
    }
}
