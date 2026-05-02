
package edu.only4.danmuku.application.queries.video_transcode

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoPostIdByFileIdQry {

    data class Request(
        val fileId: UUID
    ) : RequestParam<Response>

    data class Response(
        val filePostId: UUID,
        val filePath: String?,
        val videoPostId: UUID,
        val fileIndex: Int
    )

}

