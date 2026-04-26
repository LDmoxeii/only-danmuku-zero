package edu.only4.danmuku.application.commands.customer_profile

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object RewardUserForVideoCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                rewarded = TODO("set rewarded"),
                coinAmount = TODO("set coinAmount")
            )
        }
    }

    data class Request(
        val customerId: Long
    ) : RequestParam<Response>

    data class Response(
        val rewarded: Boolean,
        val coinAmount: Int
    )

}
