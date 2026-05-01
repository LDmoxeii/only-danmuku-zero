package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.enums.PostType
import org.springframework.stereotype.Service

object UpdateVideoPostCmd {

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
        val videoName: String?,
        val videoCover: String?,
        val pCategoryId: Long?,
        val categoryId: Long?,
        val postType: PostType?,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val uploadFileList: List<VideoPostFileSpec>
    ) : RequestParam<Response> {
        data class VideoPostFileSpec(
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
