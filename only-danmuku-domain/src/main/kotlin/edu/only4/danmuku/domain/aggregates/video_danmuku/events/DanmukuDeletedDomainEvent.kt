package edu.only4.danmuku.domain.aggregates.video_danmuku.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_danmuku.VideoDanmuku

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoDanmuku",
    name = "DanmukuDeletedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class DanmukuDeletedDomainEvent(
    val entity: VideoDanmuku,
    val entity: VideoDanmuku
) {
}
