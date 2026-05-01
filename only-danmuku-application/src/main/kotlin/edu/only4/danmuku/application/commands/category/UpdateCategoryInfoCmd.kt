package edu.only4.danmuku.application.commands.category

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object UpdateCategoryInfoCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val categoryId: Long,
        val parentId: Long = 0L,
        val code: String,
        val name: String,
        val icon: String?,
        val background: String?
    ) : RequestParam<Response>

    data object Response

}
