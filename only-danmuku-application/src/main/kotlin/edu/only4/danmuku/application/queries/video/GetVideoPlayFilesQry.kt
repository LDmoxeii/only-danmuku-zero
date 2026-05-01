
package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoPlayFilesQry {

    data class Request(
        val videoId: Long
    ) : RequestParam<Response>

    data class Response(
        val items: List<FileItem>
    ) {
        data class FileItem(
            val fileId: Long,
            val videoId: Long,
            val fileIndex: Int?,
            val fileName: String?,
            val fileSize: Long?,
            val filePath: String?,
            val duration: Int?
        )
    }

}
