
package edu.only4.danmuku.application.queries.video_encrypt

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoQualityAuthQry {

    data class Request(
        val videoFilePostId: UUID?,
        val videoFileId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val policiesJson: String
    )

}

