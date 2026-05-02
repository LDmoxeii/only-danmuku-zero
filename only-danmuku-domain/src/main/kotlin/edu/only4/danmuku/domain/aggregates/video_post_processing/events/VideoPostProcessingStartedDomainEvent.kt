package edu.only4.danmuku.domain.aggregates.video_post_processing.events

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing

/**
 *
 */
@DomainEvent(persist = false)
@Aggregate(
    aggregate = "VideoPostProcessing",
    name = "VideoPostProcessingStartedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoPostProcessingStartedDomainEvent(
    val entity: VideoPostProcessing,
    val videoPostId: UUID,
    val fileList: List<FileItem>
) {
    data class FileItem(
        val uploadId: UUID,
        val fileIndex: Int,
        val outputDir: String,
        val objectPrefix: String,
        val encOutputDir: String
    )
}

