package edu.only4.danmuku.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object CleanupTempUploadDirCli {

    data class Request(
        val tempPath: String
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val failReason: String?
    )

}
