package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.TranscodeVideoFileToAbrByPathCli
import org.springframework.stereotype.Service

@Service
class TranscodeVideoFileToAbrByPathCliHandler : RequestHandler<TranscodeVideoFileToAbrByPathCli.Request, TranscodeVideoFileToAbrByPathCli.Response> {

    override fun exec(request: TranscodeVideoFileToAbrByPathCli.Request): TranscodeVideoFileToAbrByPathCli.Response {
        return TranscodeVideoFileToAbrByPathCli.Response(
            accepted = TODO("set accepted"),
            variantsJson = TODO("set variantsJson"),
            failReason = TODO("set failReason")
        )
    }
}
