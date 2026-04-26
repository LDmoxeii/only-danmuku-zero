package edu.only4.danmuku.application.commands.video_post_processing

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object StartVideoPostProcessingCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val videoPostId: Long,
        val fileList: List<FileList>
    ) : RequestParam<Response> {
        data class FileList(
            val uploadId: Long,
            val fileIndex: Int,
            val fileName: String?,
            val fileSize: Long?,
            val duration: Int?
        )
    }

    data object Response

}
