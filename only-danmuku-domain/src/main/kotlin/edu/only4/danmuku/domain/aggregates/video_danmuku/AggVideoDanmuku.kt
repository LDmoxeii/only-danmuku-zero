package edu.only4.danmuku.domain.aggregates.video_danmuku

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_danmuku.VideoDanmuku
import edu.only4.danmuku.domain.aggregates.video_danmuku.factory.VideoDanmukuFactory

/**
 * VideoDanmuku aggregate wrapper
 * 视频弹幕
 */
class AggVideoDanmuku(
    payload: VideoDanmukuFactory.Payload? = null,
) : Aggregate.Default<VideoDanmuku>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoDanmuku, UUID>(key)
}

