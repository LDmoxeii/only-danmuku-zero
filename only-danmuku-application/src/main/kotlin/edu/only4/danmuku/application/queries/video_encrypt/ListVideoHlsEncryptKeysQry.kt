
package edu.only4.danmuku.application.queries.video_encrypt

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoHlsEncryptKeysQry {

    data class Request(
        val videoPostId: UUID,
        val fileIndex: Int,
        val keyVersion: Int?
    ) : RequestParam<Response>

    data class Response(
        val keysJson: String
    )

}

