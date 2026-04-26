package edu.only4.danmuku.application.queries.video_storage

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoHlsResourceUrlQry {

    data class Request(
        val videoFileId: Long,
        val relativePath: String
    ) : RequestParam<Response>

    data class Response(
        val url: String,
        val contentType: String?
    )

}
