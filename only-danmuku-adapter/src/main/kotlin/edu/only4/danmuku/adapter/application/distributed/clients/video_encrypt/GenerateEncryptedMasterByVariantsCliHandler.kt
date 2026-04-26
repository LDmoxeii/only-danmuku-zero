package edu.only4.danmuku.adapter.application.distributed.clients.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_encrypt.GenerateEncryptedMasterByVariantsCli
import org.springframework.stereotype.Service

@Service
class GenerateEncryptedMasterByVariantsCliHandler : RequestHandler<GenerateEncryptedMasterByVariantsCli.Request, GenerateEncryptedMasterByVariantsCli.Response> {

    override fun exec(request: GenerateEncryptedMasterByVariantsCli.Request): GenerateEncryptedMasterByVariantsCli.Response {
        return GenerateEncryptedMasterByVariantsCli.Response(
            success = TODO("set success"),
            masterPath = TODO("set masterPath"),
            failReason = TODO("set failReason")
        )
    }
}
