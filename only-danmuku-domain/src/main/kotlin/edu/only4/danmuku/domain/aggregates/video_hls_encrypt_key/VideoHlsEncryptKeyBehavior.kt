package edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key

import java.util.UUID

import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus

fun VideoHlsEncryptKey.markRevoked(reason: String? = null) {
    status = EncryptKeyStatus.REVOKED
    if (!reason.isNullOrBlank()) {
        remark = reason
    }
}

fun VideoHlsEncryptKey.bindVideoId(videoId: UUID) {
    if (this.videoId == videoId) return
    this.videoId = videoId
}

