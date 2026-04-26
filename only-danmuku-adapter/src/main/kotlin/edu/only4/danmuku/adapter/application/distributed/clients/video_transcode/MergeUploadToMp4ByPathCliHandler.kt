package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.MergeUploadToMp4ByPathCli
import org.springframework.stereotype.Service

@Service
class MergeUploadToMp4ByPathCliHandler : RequestHandler<MergeUploadToMp4ByPathCli.Request, MergeUploadToMp4ByPathCli.Response> {

    override fun exec(request: MergeUploadToMp4ByPathCli.Request): MergeUploadToMp4ByPathCli.Response {
        return MergeUploadToMp4ByPathCli.Response(
            success = TODO("set success"),
            outputDir = TODO("set outputDir"),
            mergedMp4Path = TODO("set mergedMp4Path"),
            duration = TODO("set duration"),
            fileSize = TODO("set fileSize"),
            failReason = TODO("set failReason")
        )
    }
}
