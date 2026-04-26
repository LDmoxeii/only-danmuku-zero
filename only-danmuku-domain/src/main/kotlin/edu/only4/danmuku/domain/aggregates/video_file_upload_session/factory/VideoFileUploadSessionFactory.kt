package edu.only4.danmuku.domain.aggregates.video_file_upload_session.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoFileUploadSession",
    name = "VideoFileUploadSessionFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoFileUploadSessionFactory : AggregateFactory<VideoFileUploadSessionFactory.Payload, VideoFileUploadSession> {

    override fun create(payload: Payload): VideoFileUploadSession {
        TODO("Implement aggregate construction")
    }

    @Aggregate(
        aggregate = "VideoFileUploadSession",
        name = "Payload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
        val name: String
    ) : AggregatePayload<VideoFileUploadSession>
}
