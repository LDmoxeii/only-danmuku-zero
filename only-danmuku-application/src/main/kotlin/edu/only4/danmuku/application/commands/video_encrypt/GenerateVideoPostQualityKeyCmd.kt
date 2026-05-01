package edu.only4.danmuku.application.commands.video_encrypt

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object GenerateVideoPostQualityKeyCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                success = TODO("set success")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val quality: String,
        val keyVersion: Int,
        val method: String = "HLS_AES_128",
        val keyBytes: Int = 16
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true
    )

}
