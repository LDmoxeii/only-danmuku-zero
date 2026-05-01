package edu.only4.danmuku.adapter.application.distributed.clients.video_transcode

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_transcode.UploadVideoAbrOutputCli
import org.springframework.stereotype.Service

@Service
class UploadVideoAbrOutputCliHandler : RequestHandler<UploadVideoAbrOutputCli.Request, UploadVideoAbrOutputCli.Response> {

    override fun exec(request: UploadVideoAbrOutputCli.Request): UploadVideoAbrOutputCli.Response {
        return UploadVideoAbrOutputCli.Response(
            success = TODO("set success"),
            storagePrefix = TODO("set storagePrefix"),
            failReason = TODO("set failReason")
        )
    }
}
