package edu.only4.danmuku.application.commands.video

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object TransferVideoToProductionCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                videoId = TODO("set videoId")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val customerId: Long,
        val videoCover: String,
        val videoName: String,
        val parentCategoryId: Long,
        val categoryId: Long?,
        val postType: Int,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val duration: Int
    ) : RequestParam<Response>

    data class Response(
        val videoId: Long
    )

}
