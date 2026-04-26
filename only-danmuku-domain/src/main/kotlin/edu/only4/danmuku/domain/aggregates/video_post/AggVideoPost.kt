package edu.only4.danmuku.domain.aggregates.video_post

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import edu.only4.danmuku.domain.aggregates.video_post.factory.VideoPostFactory

/**
 * VideoPost aggregate wrapper
 * 视频信息
 */
class AggVideoPost(
    payload: VideoPostFactory.Payload? = null,
) : Aggregate.Default<VideoPost>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoPost, Long>(key)
}
