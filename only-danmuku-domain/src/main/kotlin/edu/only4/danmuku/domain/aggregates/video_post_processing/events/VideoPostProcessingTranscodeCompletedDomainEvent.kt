package edu.only4.danmuku.domain.aggregates.video_post_processing.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoPostProcessing",
    name = "VideoPostProcessingTranscodeCompletedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoPostProcessingTranscodeCompletedDomainEvent(
    val entity: VideoPostProcessing,
    val videoPostId: Long,
    val fileIndex: Int,
    val outputPrefix: String?,
    val encOutputDir: String?,
    val variantsJson: String?
) {
}
