
package edu.only4.danmuku.application.queries.video_post

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostUploadIdQry {

    data class Request(
        val uploadId: Long,
        val customerId: Long,
        val excludeVideoFilePostId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
