package edu.only4.danmuku.application.commands.video_comment

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object PostCommentCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                commentId = TODO("set commentId")
            )
        }
    }

    data class Request(
        val videoId: Long,
        val replyCommentId: Long?,
        val customerId: Long,
        val replyCustomerId: Long,
        val content: String,
        val imgPath: String?
    ) : RequestParam<Response>

    data class Response(
        val commentId: Long
    )

}
