package edu.only4.danmuku.domain.aggregates.video_quality_policy

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_quality_policy.VideoQualityPolicy
import edu.only4.danmuku.domain.aggregates.video_quality_policy.factory.VideoQualityPolicyFactory

/**
 * VideoQualityPolicy aggregate wrapper
 * 视频清晰度策略
 */
class AggVideoQualityPolicy(
    payload: VideoQualityPolicyFactory.Payload? = null,
) : Aggregate.Default<VideoQualityPolicy>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoQualityPolicy, UUID>(key)
}

