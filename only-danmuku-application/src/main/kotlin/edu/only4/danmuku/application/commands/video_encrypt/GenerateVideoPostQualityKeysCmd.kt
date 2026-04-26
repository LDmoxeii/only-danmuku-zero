package edu.only4.danmuku.application.commands.video_encrypt

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object GenerateVideoPostQualityKeysCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                keyVersion = TODO("set keyVersion"),
                keysJson = TODO("set keysJson")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val qualities: List<String>,
        val method: String = "HLS_AES_128",
        val keyBytes: Int = 16
    ) : RequestParam<Response>

    data class Response(
        val keyVersion: Int,
        val keysJson: String
    )

}
