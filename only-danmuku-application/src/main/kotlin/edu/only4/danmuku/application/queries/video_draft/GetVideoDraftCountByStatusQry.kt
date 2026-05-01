
package edu.only4.danmuku.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoDraftCountByStatusQry {

    data class Request(
        val userId: Long,
        val status: VideoStatus? = null,
        val excludeStatusArray: List<VideoStatus>? = null
    ) : RequestParam<Response>

    data class Response(
        val count: Long
    )

}
