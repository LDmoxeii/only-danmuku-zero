package edu.only4.danmuku.application.commands.video_post

import java.util.UUID

import edu.only4.danmuku.domain.aggregates.video_quality_policy.*

import edu.only4.danmuku.domain.aggregates.video_post_processing.*

import edu.only4.danmuku.domain.aggregates.video_post.*

import edu.only4.danmuku.domain.aggregates.video_play_history.*

import edu.only4.danmuku.domain.aggregates.video_hls_key_token.*

import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.*

import edu.only4.danmuku.domain.aggregates.video_danmuku.*

import edu.only4.danmuku.domain.aggregates.video_comment.*

import edu.only4.danmuku.domain.aggregates.video.*

import edu.only4.danmuku.domain.aggregates.user.*

import edu.only4.danmuku.domain.aggregates.statistics.*

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.*

import edu.only4.danmuku.domain.aggregates.customer_video_series.*

import edu.only4.danmuku.domain.aggregates.customer_profile.*

import edu.only4.danmuku.domain.aggregates.customer_message.*

import edu.only4.danmuku.domain.aggregates.category.*

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.application.validators.MaxVideoPCount
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import edu.only4.danmuku.domain.aggregates.video_post.VideoPostTranscodeFileSpec
import edu.only4.danmuku.domain._share.enums.PostType
import edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult
import edu.only4.danmuku.domain.aggregates.video_post.factory.VideoPostFactory
import org.springframework.stereotype.Service

object CreateVideoPostCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val post = Mediator.factories.create(
                VideoPostFactory.Payload(
                    customerId = request.customerId,
                    videoName = request.videoName,
                    videoCover = request.videoCover,
                    pCategoryId = request.parentCategoryId,
                    categoryId = request.categoryId,
                    postType = request.postType,
                    originInfo = request.originInfo,
                    tags = request.tags,
                    introduction = request.introduction,
                    interaction = request.interaction,
                )
            )
            val transcodeFiles = request.uploadFileList.map { spec ->
                VideoPostTranscodeFileSpec(
                    uploadId = spec.uploadId,
                    fileIndex = spec.fileIndex,
                    fileName = spec.fileName,
                    fileSize = spec.fileSize,
                    duration = spec.duration
                )
            }
            post.videoFilePosts.addAll(
                transcodeFiles.map { spec -> videoFilePostFromSpec(post.id, request.customerId, spec) }
            )
            post.markTranscoding(transcodeFiles)

            Mediator.uow.save()

            return Response(videoId = post.id)
        }
    }

    @MaxVideoPCount(countField = "uploadFileList", videoIdField = "videoId")
    data class Request(
        val customerId: UUID,
        val videoName: String,
        val videoCover: String? = null,
        val parentCategoryId: UUID,
        val categoryId: UUID? = null,
        val postType: PostType = PostType.ORIGINAL,
        val originInfo: String? = null,
        val tags: String? = null,
        val introduction: String? = null,
        val interaction: String?,
        val uploadFileList: List<VideoPostFileSpec>,
    ) : RequestParam<Response>

    data class Response(
        val videoId: UUID,
    )

    data class VideoPostFileSpec(
        val uploadId: UUID,
        val fileIndex: Int,
        val fileName: String,
        val fileSize: Long?,
        val duration: Int?,
    )
}

