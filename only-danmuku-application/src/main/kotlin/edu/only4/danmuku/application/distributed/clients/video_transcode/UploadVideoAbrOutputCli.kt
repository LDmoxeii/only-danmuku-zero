package edu.only4.danmuku.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object UploadVideoAbrOutputCli {

    data class Request(
        val outputDir: String,
        val objectPrefix: String
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val storagePrefix: String?,
        val failReason: String?
    )

}
