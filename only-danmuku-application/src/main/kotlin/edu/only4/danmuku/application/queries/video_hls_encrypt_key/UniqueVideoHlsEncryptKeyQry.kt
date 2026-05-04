
package edu.only4.danmuku.application.queries.video_hls_encrypt_key

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoHlsEncryptKeyQry {

    data class Request(
        val videoPostId: UUID,
        val fileIndex: Int,
        val keyId: String,
        val keyVersion: Int,
        val quality: String,
        val excludeVideoHlsEncryptKeyId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

