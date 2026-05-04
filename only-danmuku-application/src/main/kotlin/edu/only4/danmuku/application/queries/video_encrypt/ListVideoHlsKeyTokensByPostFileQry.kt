
package edu.only4.danmuku.application.queries.video_encrypt

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoHlsKeyTokensByPostFileQry {

    data class Request(
        val videoPostId: UUID,
        val fileIndex: Int
    ) : RequestParam<Response>

    data class Response(
        val items: List<TokenItem>
    ) {
        data class TokenItem(
            val tokenId: UUID
        )
    }

}

