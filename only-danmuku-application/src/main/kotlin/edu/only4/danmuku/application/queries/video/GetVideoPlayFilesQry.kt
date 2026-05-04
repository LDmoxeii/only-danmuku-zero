
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoPlayFilesQry {

    data class Request(
        val videoId: UUID
    ) : RequestParam<Response>

    data class Response(
        val items: List<FileItem>
    ) {
        data class FileItem(
            val fileId: UUID,
            val videoId: UUID,
            val fileIndex: Int?,
            val fileName: String?,
            val fileSize: Long?,
            val filePath: String?,
            val duration: Int?
        )
    }

}

