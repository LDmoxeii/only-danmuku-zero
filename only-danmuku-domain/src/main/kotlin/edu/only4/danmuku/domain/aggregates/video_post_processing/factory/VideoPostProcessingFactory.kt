package edu.only4.danmuku.domain.aggregates.video_post_processing.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPostProcessing",
    name = "VideoPostProcessingFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoPostProcessingFactory : AggregateFactory<VideoPostProcessingFactory.Payload, VideoPostProcessing> {

    override fun create(payload: Payload): VideoPostProcessing {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoPostProcessing",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoPostProcessing>
}
