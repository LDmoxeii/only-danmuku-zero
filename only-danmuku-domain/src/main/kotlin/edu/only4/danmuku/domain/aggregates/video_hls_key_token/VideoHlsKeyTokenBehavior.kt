package edu.only4.danmuku.domain.aggregates.video_hls_key_token

import edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus

fun VideoHlsKeyToken.markExpired() {
    status = EncryptTokenStatus.EXPIRED
}

fun VideoHlsKeyToken.markExhausted() {
    status = EncryptTokenStatus.EXHAUSTED
}

fun VideoHlsKeyToken.consumeOnce() {
    usedCount += 1
    if (usedCount >= maxUse) {
        status = EncryptTokenStatus.EXHAUSTED
    }
}

fun VideoHlsKeyToken.bindVideoId(videoId: Long) {
    if (this.videoId == videoId) return
    this.videoId = videoId
}
