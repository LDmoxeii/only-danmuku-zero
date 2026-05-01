package edu.only4.danmuku.adapter.application.distributed.clients.file_upload_session

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.file_upload_session.CreateUploadSessionTempDirCli
import org.springframework.stereotype.Service

@Service
class CreateUploadSessionTempDirCliHandler : RequestHandler<CreateUploadSessionTempDirCli.Request, CreateUploadSessionTempDirCli.Response> {

    override fun exec(request: CreateUploadSessionTempDirCli.Request): CreateUploadSessionTempDirCli.Response {
        return CreateUploadSessionTempDirCli.Response(
            tempPath = TODO("set tempPath")
        )
    }
}
