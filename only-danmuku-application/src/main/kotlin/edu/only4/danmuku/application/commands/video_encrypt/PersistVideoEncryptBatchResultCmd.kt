package edu.only4.danmuku.application.commands.video_encrypt

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object PersistVideoEncryptBatchResultCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                encryptStatus = TODO("set encryptStatus")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val success: Boolean,
        val encryptMethod: String = "HLS_AES_128",
        val keyVersion: Int,
        val encryptedMasterPath: String?,
        val encryptedVariants: String?,
        val failReason: String?
    ) : RequestParam<Response>

    data class Response(
        val encryptStatus: Int
    )

}
