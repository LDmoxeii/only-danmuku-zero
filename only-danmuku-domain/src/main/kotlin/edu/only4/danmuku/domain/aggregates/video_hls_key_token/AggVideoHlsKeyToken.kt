package edu.only4.danmuku.domain.aggregates.video_hls_key_token

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.factory.VideoHlsKeyTokenFactory

/**
 * VideoHlsKeyToken aggregate wrapper
 * HLS 加密播放 token
 */
class AggVideoHlsKeyToken(
    payload: VideoHlsKeyTokenFactory.Payload? = null,
) : Aggregate.Default<VideoHlsKeyToken>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoHlsKeyToken, UUID>(key)
}

