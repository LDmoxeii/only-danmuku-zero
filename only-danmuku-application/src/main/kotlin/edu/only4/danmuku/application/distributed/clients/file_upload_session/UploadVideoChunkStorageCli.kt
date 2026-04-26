package edu.only4.danmuku.application.distributed.clients.file_upload_session

import com.only4.cap4k.ddd.core.application.RequestParam
import org.springframework.web.multipart.MultipartFile

object UploadVideoChunkStorageCli {

    data class Request(
        val tempPath: String,
        val chunkIndex: Int,
        val chunkFile: MultipartFile
    ) : RequestParam<Response>

    data class Response(
        val storedPath: String,
        val size: Long
    )

}
