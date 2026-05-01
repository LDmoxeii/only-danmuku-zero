package edu.only4.danmuku.application.distributed.clients.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object EncryptHlsWithQualityKeysCli {

    data class Request(
        val sourceDir: String,
        val outputDir: String,
        val keysJson: String,
        val segmentExt: String = ".ts.enc"
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val encryptedMasterPath: String,
        val encryptedVariants: String,
        val failReason: String?
    )

}
