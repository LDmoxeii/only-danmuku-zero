package edu.only4.danmuku.application.commands.video

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object ApplyVideoCoinCountDeltaCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                videoId = TODO("set videoId"),
                coinCount = TODO("set coinCount"),
                appliedDelta = TODO("set appliedDelta")
            )
        }
    }

    data class Request(
        val videoId: Long,
        val delta: Int = 1
    ) : RequestParam<Response>

    data class Response(
        val videoId: Long,
        val coinCount: Int,
        val appliedDelta: Int
    )

}
