package edu.only4.danmuku.domain.aggregates.video_file_upload_session.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoFileUploadSession",
    name = "UploadSessionCreatedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class UploadSessionCreatedDomainEvent(
    val entity: VideoFileUploadSession
) {
}
