package edu.only4.danmuku.application.commands.video_encrypt

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object IssueVideoHlsKeyTokenCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                token = TODO("set token"),
                expireAt = TODO("set expireAt"),
                keyVersion = TODO("set keyVersion"),
                allowedQualities = TODO("set allowedQualities")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val videoId: Long,
        val fileIndex: Int,
        val keyVersion: Int,
        val audience: String?,
        val expireSeconds: Int = 600,
        val maxUse: Int = 30,
        val allowedQualities: String?
    ) : RequestParam<Response>

    data class Response(
        val token: String,
        val expireAt: Long,
        val keyVersion: Int?,
        val allowedQualities: String?
    )

}
