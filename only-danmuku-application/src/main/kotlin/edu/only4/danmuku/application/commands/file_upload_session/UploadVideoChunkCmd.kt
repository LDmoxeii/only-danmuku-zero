package edu.only4.danmuku.application.commands.file_upload_session

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object UploadVideoChunkCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val customerId: Long,
        val uploadId: Long,
        val chunkIndex: Int,
        val chunkSize: Long
    ) : RequestParam<Response>

    data object Response

}
