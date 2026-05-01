package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.CleanupMergedMp4Cli
import org.springframework.stereotype.Service

@Service
class CleanupMergedMp4CliHandler : RequestHandler<CleanupMergedMp4Cli.Request, CleanupMergedMp4Cli.Response> {

    override fun exec(request: CleanupMergedMp4Cli.Request): CleanupMergedMp4Cli.Response {
        return CleanupMergedMp4Cli.Response(
            success = TODO("set success"),
            failReason = TODO("set failReason")
        )
    }
}
