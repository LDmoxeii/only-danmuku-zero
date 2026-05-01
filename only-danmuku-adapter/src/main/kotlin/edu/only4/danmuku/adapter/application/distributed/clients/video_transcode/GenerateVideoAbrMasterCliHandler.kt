package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.GenerateVideoAbrMasterCli
import org.springframework.stereotype.Service

@Service
class GenerateVideoAbrMasterCliHandler : RequestHandler<GenerateVideoAbrMasterCli.Request, GenerateVideoAbrMasterCli.Response> {

    override fun exec(request: GenerateVideoAbrMasterCli.Request): GenerateVideoAbrMasterCli.Response {
        return GenerateVideoAbrMasterCli.Response(
            success = TODO("set success"),
            masterPath = TODO("set masterPath"),
            failReason = TODO("set failReason")
        )
    }
}
