package edu.only4.danmuku.application.commands.file_upload_session

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object DeleteUploadSessionCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                tempPath = TODO("set tempPath")
            )
        }
    }

    data class Request(
        val customerId: Long,
        val uploadId: Long
    ) : RequestParam<Response>

    data class Response(
        val tempPath: String?
    )

}
