package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus
import org.springframework.stereotype.Service

object SyncVideoPostProcessStatusCmd {

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
        val targetStatus: VideoStatus,
        val duration: Int?,
        val failReason: String?,
        val fileList: List<FileItem>
    ) : RequestParam<Response> {
        data class FileItem(
            val fileIndex: Int,
            val transcodeOutputPrefix: String?,
            val encryptOutputPrefix: String?,
            val variants: List<VariantItem>,
            val duration: Int?,
            val fileSize: Long?,
            val encryptMethod: String?,
            val keyVersion: Int?
        )
        data class VariantItem(
            val quality: String = "",
            val width: Int = 0,
            val height: Int = 0,
            val videoBitrateKbps: Int = 0,
            val audioBitrateKbps: Int = 0,
            val bandwidthBps: Int = 0,
            val playlistPath: String = "",
            val segmentPrefix: String?,
            val segmentDuration: Int?
        )
    }

    data class Response(
        val success: Boolean = true
    )

}
