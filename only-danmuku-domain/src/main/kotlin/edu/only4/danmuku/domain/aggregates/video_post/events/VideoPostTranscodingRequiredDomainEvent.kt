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
    name = "VideoPostTranscodingRequiredDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoPostTranscodingRequiredDomainEvent(
    val entity: VideoPost,
    val entity: VideoPost
) {
}
