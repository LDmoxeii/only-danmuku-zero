package edu.only4.danmuku.domain.aggregates.video_post.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPost",
    name = "VideoPostFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoPostFactory : AggregateFactory<VideoPostFactory.Payload, VideoPost> {

    override fun create(payload: Payload): VideoPost {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoPost",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoPost>
}
