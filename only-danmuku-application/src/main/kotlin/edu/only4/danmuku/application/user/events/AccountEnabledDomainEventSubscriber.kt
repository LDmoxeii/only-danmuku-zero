package edu.only4.danmuku.application.user.events

import edu.only4.danmuku.domain.aggregates.user.events.AccountEnabledDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class AccountEnabledDomainEventSubscriber {

    @EventListener(AccountEnabledDomainEvent::class)
    fun on(event: AccountEnabledDomainEvent) {
    }
}
