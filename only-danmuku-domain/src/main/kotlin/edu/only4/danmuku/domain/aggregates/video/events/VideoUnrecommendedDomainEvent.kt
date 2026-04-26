package edu.only4.danmuku.domain.aggregates.video.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video.Video

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "Video",
    name = "VideoUnrecommendedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoUnrecommendedDomainEvent(
    val entity: Video,
    val entity: Video
) {
}
