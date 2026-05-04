package edu.only4.danmuku.domain.aggregates.video_hls_key_token.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken

/**
 *
 */
@DomainEvent(persist = true)
@Aggregate(
    aggregate = "VideoHlsKeyToken",
    name = "VideoHlsKeyTokenIssuedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoHlsKeyTokenIssuedDomainEvent(
    val entity: VideoHlsKeyToken
) {
}
