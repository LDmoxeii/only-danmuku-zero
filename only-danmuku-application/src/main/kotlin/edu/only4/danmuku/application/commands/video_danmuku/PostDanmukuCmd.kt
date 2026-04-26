package edu.only4.danmuku.application.commands.video_danmuku

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object PostDanmukuCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val videoId: Long,
        val fileId: Long,
        val customerId: Long,
        val text: String,
        val mode: Int,
        val color: String,
        val time: Int
    ) : RequestParam<Response>

    data object Response

}
