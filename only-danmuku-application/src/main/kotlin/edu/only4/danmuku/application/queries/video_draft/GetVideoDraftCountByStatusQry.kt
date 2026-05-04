
package edu.only4.danmuku.application.queries.video_draft

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoDraftCountByStatusQry {

    data class Request(
        val userId: UUID,
        val status: VideoStatus? = null,
        val excludeStatusArray: List<VideoStatus>? = null
    ) : RequestParam<Response>

    data class Response(
        val count: Long
    )

}

