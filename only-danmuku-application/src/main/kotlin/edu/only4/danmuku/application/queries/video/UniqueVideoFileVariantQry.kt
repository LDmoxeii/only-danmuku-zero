
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFileVariantQry {

    data class Request(
        val fileId: UUID,
        val quality: String,
        val excludeVideoFileVariantId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

