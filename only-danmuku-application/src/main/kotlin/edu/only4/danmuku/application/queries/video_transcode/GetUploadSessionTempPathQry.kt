
package edu.only4.danmuku.application.queries.video_transcode

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetUploadSessionTempPathQry {

    data class Request(
        val uploadId: UUID
    ) : RequestParam<Response>

    data class Response(
        val tempPath: String
    )

}

