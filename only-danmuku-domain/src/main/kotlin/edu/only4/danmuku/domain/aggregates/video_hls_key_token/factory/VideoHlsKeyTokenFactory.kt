package edu.only4.danmuku.domain.aggregates.video_hls_key_token.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoHlsKeyToken",
    name = "VideoHlsKeyTokenFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoHlsKeyTokenFactory : AggregateFactory<VideoHlsKeyTokenFactory.Payload, VideoHlsKeyToken> {

    override fun create(payload: Payload): VideoHlsKeyToken {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoHlsKeyToken",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoHlsKeyToken>
}
