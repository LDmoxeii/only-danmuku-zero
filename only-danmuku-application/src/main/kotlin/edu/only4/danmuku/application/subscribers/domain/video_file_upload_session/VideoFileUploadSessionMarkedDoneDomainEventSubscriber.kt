package edu.only4.danmuku.application.subscribers.domain.video_file_upload_session

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.VideoFileUploadSessionMarkedDoneDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoFileUploadSessionMarkedDoneDomainEventSubscriber {

    @EventListener(VideoFileUploadSessionMarkedDoneDomainEvent::class)
    fun on(event: VideoFileUploadSessionMarkedDoneDomainEvent) {
    }
}
