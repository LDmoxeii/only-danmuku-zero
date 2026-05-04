
package edu.only4.danmuku.application.queries.video_file_post

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoFilePostsByPostIdQry {

    data class Request(
        val videoPostId: UUID
    ) : RequestParam<Response>

    data class Response(
        val files: List<FileItem>
    ) {
        data class FileItem(
            val videoFilePostId: UUID,
            val uploadId: UUID,
            val fileIndex: Int,
            val transferResult: Int,
            val duration: Int?
        )
    }

}

