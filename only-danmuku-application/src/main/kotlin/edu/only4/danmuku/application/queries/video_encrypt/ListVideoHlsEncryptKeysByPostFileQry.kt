
package edu.only4.danmuku.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoHlsEncryptKeysByPostFileQry {

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int
    ) : RequestParam<Response>

    data class Response(
        val items: List<EncryptKeyItem>
    ) {
        data class EncryptKeyItem(
            val encryptKeyId: Long
        )
    }

}
