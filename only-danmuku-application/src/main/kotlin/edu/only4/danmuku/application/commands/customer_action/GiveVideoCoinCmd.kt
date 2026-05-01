package edu.only4.danmuku.application.commands.customer_action

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType
import org.springframework.stereotype.Service

object GiveVideoCoinCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                coinCount = TODO("set coinCount")
            )
        }
    }

    data class Request(
        val videoId: Long,
        val customerId: Long,
        val coinCount: Int,
        val actionType: ActionType
    ) : RequestParam<Response>

    data class Response(
        val coinCount: Int
    )

}
