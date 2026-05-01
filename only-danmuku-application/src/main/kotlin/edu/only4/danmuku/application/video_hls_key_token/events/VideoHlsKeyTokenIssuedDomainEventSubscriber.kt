package edu.only4.danmuku.application.video_hls_key_token.events

import edu.only4.danmuku.domain.aggregates.video_hls_key_token.events.VideoHlsKeyTokenIssuedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoHlsKeyTokenIssuedDomainEventSubscriber {

    @EventListener(VideoHlsKeyTokenIssuedDomainEvent::class)
    fun on(event: VideoHlsKeyTokenIssuedDomainEvent) {
    }
}
