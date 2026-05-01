
package edu.only4.danmuku.application.queries.video_file_post

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoFilePostsByPostIdQry {

    data class Request(
        val videoPostId: Long
    ) : RequestParam<Response>

    data class Response(
        val files: List<FileItem>
    ) {
        data class FileItem(
            val videoFilePostId: Long,
            val uploadId: Long,
            val fileIndex: Int,
            val transferResult: Int,
            val duration: Int?
        )
    }

}
