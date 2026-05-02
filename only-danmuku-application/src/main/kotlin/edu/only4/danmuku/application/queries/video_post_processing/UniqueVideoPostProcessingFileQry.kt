
package edu.only4.danmuku.application.queries.video_post_processing

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoPostProcessingFileQry {

    data class Request(
        val parentId: UUID,
        val fileIndex: Int,
        val excludeVideoPostProcessingFileId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

