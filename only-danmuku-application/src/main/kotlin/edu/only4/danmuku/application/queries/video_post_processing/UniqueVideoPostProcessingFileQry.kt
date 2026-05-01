
package edu.only4.danmuku.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoPostProcessingFileQry {

    data class Request(
        val parentId: Long,
        val fileIndex: Int,
        val excludeVideoPostProcessingFileId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
