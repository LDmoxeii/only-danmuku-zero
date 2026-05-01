package edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.VideoHlsEncryptKey
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.factory.VideoHlsEncryptKeyFactory

/**
 * VideoHlsEncryptKey aggregate wrapper
 * 视频 HLS 加密密钥
 */
class AggVideoHlsEncryptKey(
    payload: VideoHlsEncryptKeyFactory.Payload? = null,
) : Aggregate.Default<VideoHlsEncryptKey>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoHlsEncryptKey, Long>(key)
}
