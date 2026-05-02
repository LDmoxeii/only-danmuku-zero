
package edu.only4.danmuku.application.queries.video_post

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostUploadIdQry {

    data class Request(
        val uploadId: UUID,
        val customerId: UUID,
        val excludeVideoFilePostId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

