
package edu.only4.danmuku.application.queries.video_transcode

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoAbrVariantsQry {

    data class Request(
        val fileId: UUID
    ) : RequestParam<Response>

    data class Response(
        val qualities: List<String>,
        val variantJson: String
    )

}

