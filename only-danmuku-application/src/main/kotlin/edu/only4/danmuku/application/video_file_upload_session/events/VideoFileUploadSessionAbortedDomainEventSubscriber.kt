package edu.only4.danmuku.application.video_file_upload_session.events

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.VideoFileUploadSessionAbortedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoFileUploadSessionAbortedDomainEventSubscriber {

    @EventListener(VideoFileUploadSessionAbortedDomainEvent::class)
    fun on(event: VideoFileUploadSessionAbortedDomainEvent) {
    }
}
