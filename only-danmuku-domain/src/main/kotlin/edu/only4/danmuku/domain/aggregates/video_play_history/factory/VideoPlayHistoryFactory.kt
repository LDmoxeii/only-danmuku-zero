package edu.only4.danmuku.domain.aggregates.video_play_history.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_play_history.VideoPlayHistory
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPlayHistory",
    name = "VideoPlayHistoryFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoPlayHistoryFactory : AggregateFactory<VideoPlayHistoryFactory.Payload, VideoPlayHistory> {

    override fun create(payload: Payload): VideoPlayHistory {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoPlayHistory",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoPlayHistory>
}
