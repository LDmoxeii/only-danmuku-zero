package edu.only4.danmuku.application.subscribers.domain.video_file_upload_session

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.UploadSessionAbortedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class UploadSessionAbortedDomainEventSubscriber {

    @EventListener(UploadSessionAbortedDomainEvent::class)
    fun on(event: UploadSessionAbortedDomainEvent) {
    }
}
