package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object TranscodeAllTranscodingFilesCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                total = TODO("set total"),
                success = TODO("set success"),
                failed = TODO("set failed")
            )
        }
    }

    data class Request(
        val videoPostId: Long
    ) : RequestParam<Response>

    data class Response(
        val total: Int,
        val success: Int,
        val failed: Int
    )

}
