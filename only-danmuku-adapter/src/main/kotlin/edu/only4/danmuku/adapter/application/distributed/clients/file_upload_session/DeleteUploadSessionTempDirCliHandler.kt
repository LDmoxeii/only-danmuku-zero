package edu.only4.danmuku.adapter.application.distributed.clients.file_upload_session

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.file_upload_session.DeleteUploadSessionTempDirCli
import org.springframework.stereotype.Service

@Service
class DeleteUploadSessionTempDirCliHandler : RequestHandler<DeleteUploadSessionTempDirCli.Request, DeleteUploadSessionTempDirCli.Response> {

    override fun exec(request: DeleteUploadSessionTempDirCli.Request): DeleteUploadSessionTempDirCli.Response {
        return DeleteUploadSessionTempDirCli.Response(
            success = TODO("set success"),
            failReason = TODO("set failReason")
        )
    }
}
