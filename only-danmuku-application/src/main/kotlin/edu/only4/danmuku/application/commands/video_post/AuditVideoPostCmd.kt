package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus
import org.springframework.stereotype.Service

object AuditVideoPostCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val videoPostId: Long,
        val status: VideoStatus,
        val reason: String
    ) : RequestParam<Response>

    data object Response

}
