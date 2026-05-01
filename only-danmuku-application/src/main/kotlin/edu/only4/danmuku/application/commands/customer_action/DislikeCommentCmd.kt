package edu.only4.danmuku.application.commands.customer_action

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object DislikeCommentCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                isCancel = TODO("set isCancel"),
                hadOpposite = TODO("set hadOpposite")
            )
        }
    }

    data class Request(
        val videoId: Long,
        val commentId: Long,
        val customerId: Long
    ) : RequestParam<Response>

    data class Response(
        val isCancel: Boolean,
        val hadOpposite: Boolean
    )

}
