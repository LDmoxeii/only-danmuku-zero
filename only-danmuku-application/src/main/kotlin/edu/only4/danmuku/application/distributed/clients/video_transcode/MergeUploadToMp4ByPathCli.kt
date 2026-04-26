package edu.only4.danmuku.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object MergeUploadToMp4ByPathCli {

    data class Request(
        val tempPath: String,
        val outputDir: String
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val outputDir: String,
        val mergedMp4Path: String,
        val duration: Int?,
        val fileSize: Long?,
        val failReason: String?
    )

}
