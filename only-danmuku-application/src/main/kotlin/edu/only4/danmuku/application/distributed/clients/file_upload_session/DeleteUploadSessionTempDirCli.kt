package edu.only4.danmuku.application.distributed.clients.file_upload_session

import com.only4.cap4k.ddd.core.application.RequestParam

object DeleteUploadSessionTempDirCli {

    data class Request(
        val tempPath: String
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val failReason: String?
    )

}
