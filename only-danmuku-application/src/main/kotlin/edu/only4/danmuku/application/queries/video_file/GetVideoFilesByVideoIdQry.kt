package edu.only4.danmuku.application.queries.video_file

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoFilesByVideoIdQry {

    data class Request(
        val videoId: Long
    ) : RequestParam<Response>

    data class Response(
        val fileId: Long,
        val videoId: Long,
        val userId: Long,
        val fileIndex: Int,
        val fileName: String,
        val fileSize: Long,
        val filePath: String,
        val duration: Int
    )

}
