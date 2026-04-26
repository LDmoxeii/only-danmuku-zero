package edu.only4.danmuku.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoHlsEncryptKeysQry {

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val keyVersion: Int?
    ) : RequestParam<Response>

    data class Response(
        val keysJson: String
    )

}
