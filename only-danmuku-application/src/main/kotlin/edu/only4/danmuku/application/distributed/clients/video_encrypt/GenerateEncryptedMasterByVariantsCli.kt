package edu.only4.danmuku.application.distributed.clients.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object GenerateEncryptedMasterByVariantsCli {

    data class Request(
        val outputDir: String,
        val variantsJson: String
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val masterPath: String?,
        val failReason: String?
    )

}
