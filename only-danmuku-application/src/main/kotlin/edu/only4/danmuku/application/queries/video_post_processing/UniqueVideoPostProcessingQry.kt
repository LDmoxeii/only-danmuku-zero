
package edu.only4.danmuku.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoPostProcessingQry {

    data class Request(
        val videoPostId: Long,
        val excludeVideoPostProcessingId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
