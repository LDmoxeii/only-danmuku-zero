package edu.only4.danmuku.application.distributed.clients.file_storage

import com.only4.cap4k.ddd.core.application.RequestParam
import org.springframework.web.multipart.MultipartFile

object UploadImageResourceCli {

    data class Request(
        val file: MultipartFile,
        val createThumbnail: Boolean = false,
        val bizType: String
    ) : RequestParam<Response>

    data class Response(
        val resourceKey: String,
        val thumbnailKey: String?,
        val publicUrl: String?
    )

}
