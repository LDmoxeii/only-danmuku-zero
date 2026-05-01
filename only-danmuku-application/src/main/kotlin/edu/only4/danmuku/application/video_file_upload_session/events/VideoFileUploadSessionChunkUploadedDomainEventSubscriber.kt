package edu.only4.danmuku.application.video_file_upload_session.events

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.VideoFileUploadSessionChunkUploadedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoFileUploadSessionChunkUploadedDomainEventSubscriber {

    @EventListener(VideoFileUploadSessionChunkUploadedDomainEvent::class)
    fun on(event: VideoFileUploadSessionChunkUploadedDomainEvent) {
    }
}
