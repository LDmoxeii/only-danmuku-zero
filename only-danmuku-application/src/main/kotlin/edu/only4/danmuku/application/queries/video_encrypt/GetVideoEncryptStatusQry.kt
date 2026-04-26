package edu.only4.danmuku.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoEncryptStatusQry {

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int
    ) : RequestParam<Response>

    data class Response(
        val encryptStatus: String,
        val encryptMethod: String?,
        val keyId: String?,
        val keyVersion: Int?,
        val keyQuality: String?,
        val encryptedMasterPath: String?
    )

}
