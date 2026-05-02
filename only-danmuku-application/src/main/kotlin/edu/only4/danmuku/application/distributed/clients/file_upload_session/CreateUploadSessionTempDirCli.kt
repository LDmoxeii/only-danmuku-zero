package edu.only4.danmuku.application.distributed.clients.file_upload_session

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CreateUploadSessionTempDirCli {

    data class Request(
        val uploadId: UUID
    ) : RequestParam<Response>

    data class Response(
        val tempPath: String
    )

}

