package edu.only4.danmuku.application.commands.video_encrypt

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object ConsumeVideoHlsKeyTokenCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                valid = TODO("set valid"),
                keyPlainHex = TODO("set keyPlainHex"),
                ivHex = TODO("set ivHex"),
                failReason = TODO("set failReason")
            )
        }
    }

    data class Request(
        val token: String,
        val keyId: String,
        val quality: String
    ) : RequestParam<Response>

    data class Response(
        val valid: Boolean,
        val keyPlainHex: String?,
        val ivHex: String?,
        val failReason: String?
    )

}
