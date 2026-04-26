package edu.only4.danmuku.domain.aggregates.video_post.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoPost",
    name = "VideoAuditFailedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoAuditFailedDomainEvent(
    val entity: VideoPost
) {
}
