package edu.only4.danmuku.application.subscribers.domain.video_file_upload_session

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
