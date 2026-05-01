package edu.only4.danmuku.adapter.application.distributed.clients.file_storage

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.file_storage.UploadImageResourceCli
import org.springframework.stereotype.Service

@Service
class UploadImageResourceCliHandler : RequestHandler<UploadImageResourceCli.Request, UploadImageResourceCli.Response> {

    override fun exec(request: UploadImageResourceCli.Request): UploadImageResourceCli.Response {
        return UploadImageResourceCli.Response(
            resourceKey = TODO("set resourceKey"),
            thumbnailKey = TODO("set thumbnailKey"),
            publicUrl = TODO("set publicUrl")
        )
    }
}
