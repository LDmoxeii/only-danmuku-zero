package edu.only4.danmuku.application.commands.customer_video_series

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object UpdateCustomerVideoSeriesInfoCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                seriesId = TODO("set seriesId")
            )
        }
    }

    data class Request(
        val userId: Long,
        val seriesId: Long,
        val seriesName: String,
        val seriesDescription: String?
    ) : RequestParam<Response>

    data class Response(
        val seriesId: Long
    )

}
