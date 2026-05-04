package edu.only4.danmuku.domain.aggregates.video_post_processing

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing
import edu.only4.danmuku.domain.aggregates.video_post_processing.factory.VideoPostProcessingFactory

/**
 * VideoPostProcessing aggregate wrapper
 * 视频稿件处理聚合
 */
class AggVideoPostProcessing(
    payload: VideoPostProcessingFactory.Payload? = null,
) : Aggregate.Default<VideoPostProcessing>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoPostProcessing, UUID>(key)
}

