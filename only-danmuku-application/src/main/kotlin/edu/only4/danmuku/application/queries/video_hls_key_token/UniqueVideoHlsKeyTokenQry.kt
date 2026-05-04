
package edu.only4.danmuku.application.queries.video_hls_key_token

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoHlsKeyTokenQry {

    data class Request(
        val tokenHash: String,
        val excludeVideoHlsKeyTokenId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

