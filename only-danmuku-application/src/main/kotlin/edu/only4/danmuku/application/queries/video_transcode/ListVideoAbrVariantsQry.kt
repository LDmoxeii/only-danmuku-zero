package edu.only4.danmuku.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoAbrVariantsQry {

    data class Request(
        val fileId: Long
    ) : RequestParam<Response>

    data class Response(
        val qualities: List<String>,
        val variantJson: String
    )

}
