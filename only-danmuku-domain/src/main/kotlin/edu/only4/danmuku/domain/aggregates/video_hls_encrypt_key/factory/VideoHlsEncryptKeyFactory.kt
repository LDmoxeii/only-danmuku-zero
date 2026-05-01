package edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.VideoHlsEncryptKey
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoHlsEncryptKey",
    name = "VideoHlsEncryptKeyFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoHlsEncryptKeyFactory : AggregateFactory<VideoHlsEncryptKeyFactory.Payload, VideoHlsEncryptKey> {

    override fun create(payload: Payload): VideoHlsEncryptKey {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoHlsEncryptKey",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoHlsEncryptKey>
}
