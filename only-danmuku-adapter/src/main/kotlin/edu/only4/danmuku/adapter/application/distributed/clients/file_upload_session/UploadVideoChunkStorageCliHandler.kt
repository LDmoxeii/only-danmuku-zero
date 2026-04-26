package edu.only4.danmuku.adapter.application.distributed.clients.file_upload_session

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.file_upload_session.UploadVideoChunkStorageCli
import org.springframework.stereotype.Service

@Service
class UploadVideoChunkStorageCliHandler : RequestHandler<UploadVideoChunkStorageCli.Request, UploadVideoChunkStorageCli.Response> {

    override fun exec(request: UploadVideoChunkStorageCli.Request): UploadVideoChunkStorageCli.Response {
        return UploadVideoChunkStorageCli.Response(
            storedPath = TODO("set storedPath"),
            size = TODO("set size")
        )
    }
}
