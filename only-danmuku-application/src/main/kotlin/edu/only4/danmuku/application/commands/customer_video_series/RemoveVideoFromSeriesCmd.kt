package edu.only4.danmuku.application.commands.customer_video_series

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object RemoveVideoFromSeriesCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                deleted = TODO("set deleted")
            )
        }
    }

    data class Request(
        val seriesId: Long,
        val videoId: Long,
        val operatorId: Long
    ) : RequestParam<Response>

    data class Response(
        val deleted: Boolean
    )

}
