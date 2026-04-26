package edu.only4.danmuku.application.distributed.clients.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object EncryptHlsVariantWithKeyCli {

    data class Request(
        val sourceDir: String,
        val outputDir: String,
        val quality: String,
        val keyPlainHex: String,
        val ivHex: String?,
        val keyUriTemplate: String?,
        val segmentExt: String = ".ts.enc"
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val playlistPath: String?,
        val segmentPrefix: String?,
        val segmentCount: Int?,
        val failReason: String?
    )

}
