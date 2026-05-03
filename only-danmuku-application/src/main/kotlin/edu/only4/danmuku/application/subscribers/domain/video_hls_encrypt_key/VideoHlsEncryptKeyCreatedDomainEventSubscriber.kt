package edu.only4.danmuku.application.subscribers.domain.video_hls_encrypt_key

import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.events.VideoHlsEncryptKeyCreatedDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class VideoHlsEncryptKeyCreatedDomainEventSubscriber {

    @EventListener(VideoHlsEncryptKeyCreatedDomainEvent::class)
    fun on(event: VideoHlsEncryptKeyCreatedDomainEvent) {
    }
}
