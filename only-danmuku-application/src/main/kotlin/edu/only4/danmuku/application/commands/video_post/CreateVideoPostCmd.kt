package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain.aggregates.video_post.enums.PostType
import org.springframework.stereotype.Service

object CreateVideoPostCmd {

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
        val customerId: Long,
        val videoName: String,
        val videoCover: String?,
        val parentCategoryId: Long,
        val categoryId: Long?,
        val postType: PostType,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val uploadFileList: List<UploadFileList>
    ) : RequestParam<Response> {
        data class UploadFileList(
            val uploadId: Long,
            val fileIndex: Int,
            val fileName: String,
            val fileSize: Long?,
            val duration: Int?
        )
    }

    data class Response(
        val videoId: Long
    )

}
