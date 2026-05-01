package edu.only4.danmuku.adapter.application.distributed.clients.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.video_encrypt.EncryptHlsWithQualityKeysCli
import org.springframework.stereotype.Service

@Service
class EncryptHlsWithQualityKeysCliHandler : RequestHandler<EncryptHlsWithQualityKeysCli.Request, EncryptHlsWithQualityKeysCli.Response> {

    override fun exec(request: EncryptHlsWithQualityKeysCli.Request): EncryptHlsWithQualityKeysCli.Response {
        return EncryptHlsWithQualityKeysCli.Response(
            success = TODO("set success"),
            encryptedMasterPath = TODO("set encryptedMasterPath"),
            encryptedVariants = TODO("set encryptedVariants"),
            failReason = TODO("set failReason")
        )
    }
}
