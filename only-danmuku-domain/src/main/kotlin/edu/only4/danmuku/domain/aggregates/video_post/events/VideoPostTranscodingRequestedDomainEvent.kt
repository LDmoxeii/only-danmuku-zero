package edu.only4.danmuku.domain.aggregates.video_post.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost

/**
 *
 */
@DomainEvent(persist = true)
@Aggregate(
    aggregate = "VideoPost",
    name = "VideoPostTranscodingRequestedDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class VideoPostTranscodingRequestedDomainEvent(
    val entity: VideoPost,
    val videoPostId: Long,
    val fileList: List<FileList>,
    val entity: VideoPost
) {
    data class FileList(
        val uploadId: Long,
        val fileIndex: Int,
        val fileName: String?,
        val fileSize: Long?,
        val duration: Int?
    )
}
