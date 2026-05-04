package edu.only4.danmuku.domain.aggregates.video_play_history

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_play_history.VideoPlayHistory
import edu.only4.danmuku.domain.aggregates.video_play_history.factory.VideoPlayHistoryFactory

/**
 * VideoPlayHistory aggregate wrapper
 * 视频播放历史
 */
class AggVideoPlayHistory(
    payload: VideoPlayHistoryFactory.Payload? = null,
) : Aggregate.Default<VideoPlayHistory>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoPlayHistory, UUID>(key)
}

