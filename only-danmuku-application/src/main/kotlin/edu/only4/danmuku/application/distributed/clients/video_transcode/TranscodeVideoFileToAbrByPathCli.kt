package edu.only4.danmuku.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object TranscodeVideoFileToAbrByPathCli {

    data class Request(
        val sourcePath: String,
        val outputDir: String,
        val profiles: String,
        val segmentDurationSec: Int = 6
    ) : RequestParam<Response>

    data class Response(
        val accepted: Boolean = true,
        val variantsJson: String,
        val failReason: String?
    )

}
