
package edu.only4.danmuku.application.queries.video_transcode

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoAbrMasterQry {

    data class Request(
        val fileId: UUID
    ) : RequestParam<Response>

    data class Response(
        val status: String,
        val masterPath: String?
    )

}

