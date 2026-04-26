package edu.only4.danmuku.domain.aggregates.video_comment.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_comment.VideoComment
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoComment",
    name = "VideoCommentFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoCommentFactory : AggregateFactory<VideoCommentFactory.Payload, VideoComment> {

    override fun create(payload: Payload): VideoComment {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoComment",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoComment>
}
