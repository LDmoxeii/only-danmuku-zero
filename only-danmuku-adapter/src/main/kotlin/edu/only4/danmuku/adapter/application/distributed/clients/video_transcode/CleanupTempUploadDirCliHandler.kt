package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.CleanupTempUploadDirCli
import org.springframework.stereotype.Service

@Service
class CleanupTempUploadDirCliHandler : RequestHandler<CleanupTempUploadDirCli.Request, CleanupTempUploadDirCli.Response> {

    override fun exec(request: CleanupTempUploadDirCli.Request): CleanupTempUploadDirCli.Response {
        return CleanupTempUploadDirCli.Response(
            success = TODO("set success"),
            failReason = TODO("set failReason")
        )
    }
}
