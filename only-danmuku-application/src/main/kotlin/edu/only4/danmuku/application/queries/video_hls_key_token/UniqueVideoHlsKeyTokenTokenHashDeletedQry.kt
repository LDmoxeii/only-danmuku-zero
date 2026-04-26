package edu.only4.danmuku.application.queries.video_hls_key_token

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoHlsKeyTokenTokenHashDeletedQry {

    data class Request(
        val tokenHash: String,
        val deleted: Long,
        val excludeVideoHlsKeyTokenId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
