package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.MergeUploadToMp4Cli
import org.springframework.stereotype.Service

@Service
class MergeUploadToMp4CliHandler : RequestHandler<MergeUploadToMp4Cli.Request, MergeUploadToMp4Cli.Response> {

    override fun exec(request: MergeUploadToMp4Cli.Request): MergeUploadToMp4Cli.Response {
        return MergeUploadToMp4Cli.Response(
            success = TODO("set success"),
            outputDir = TODO("set outputDir"),
            mergedMp4Path = TODO("set mergedMp4Path"),
            duration = TODO("set duration"),
            fileSize = TODO("set fileSize"),
            failReason = TODO("set failReason")
        )
    }
}
