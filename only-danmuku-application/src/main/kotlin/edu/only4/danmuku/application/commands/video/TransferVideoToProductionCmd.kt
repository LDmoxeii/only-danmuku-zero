package edu.only4.danmuku.application.commands.video

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

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.meta.video.SVideo
import edu.only4.danmuku.domain._share.meta.video_post.SVideoPost
import edu.only4.danmuku.domain.aggregates.video.Video
import edu.only4.danmuku.domain.aggregates.video.VideoSyncFileArgs
import edu.only4.danmuku.domain.aggregates.video.VideoSyncFileVariantArgs
import edu.only4.danmuku.domain.aggregates.video.factory.VideoFactory
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost
import org.springframework.stereotype.Service

object TransferVideoToProductionCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val post = Mediator.repositories.findOne(
                SVideoPost.predicateById(request.videoPostId),
                persist = false,
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "稿件不存在: ${request.videoPostId}")
            if (post.videoFilePosts.isEmpty()) {
                throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "稿件文件不存在: ${request.videoPostId}")
            }
            val files = post.videoFilePosts.sortedBy { it.fileIndex }.map { file ->
                val variants = file.variants.map { variant ->
                    VideoSyncFileVariantArgs(
                        quality = variant.quality,
                        width = variant.width,
                        height = variant.height,
                        videoBitrateKbps = variant.videoBitrateKbps,
                        audioBitrateKbps = variant.audioBitrateKbps,
                        bandwidthBps = variant.bandwidthBps,
                        playlistPath = variant.playlistPath,
                        segmentPrefix = variant.segmentPrefix,
                        segmentDuration = variant.segmentDuration
                    )
                }
                VideoSyncFileArgs(
                    videoFilePostId = file.id,
                    customerId = file.customerId,
                    fileName = file.fileName,
                    fileIndex = file.fileIndex,
                    fileSize = file.fileSize,
                    filePath = resolveFilePath(file),
                    duration = file.duration,
                    variants = variants,
                )
            }

            val targetVideo = Mediator.repositories.findOne(
                SVideo.predicate { schema -> schema.videoPostId eq request.videoPostId }
            )?.apply {
                syncFromBasics(
                    videoPostId = request.videoPostId,
                    customerId = request.customerId,
                    videoCover = request.videoCover,
                    videoName = request.videoName,
                    parentCategoryId = request.parentCategoryId,
                    categoryId = request.categoryId,
                    postType = request.postType,
                    originInfo = request.originInfo,
                    tags = request.tags,
                    introduction = request.introduction,
                    interaction = request.interaction,
                    duration = request.duration,
                    files = files
                )
            } ?: Mediator.factories.create(
                VideoFactory.Payload(
                    videoPostId = request.videoPostId,
                    customerId = request.customerId,
                    videoCover = request.videoCover,
                    videoName = request.videoName,
                    parentCategoryId = request.parentCategoryId,
                    categoryId = request.categoryId,
                    postType = request.postType,
                    originInfo = request.originInfo,
                    tags = request.tags,
                    introduction = request.introduction,
                    interaction = request.interaction,
                    duration = request.duration,
                    files = files
                )
            )
            // 持久化变更/创建
            Mediator.uow.save()

            return Response(videoId = targetVideo.id)
        }
    }

    data class Request(
        val videoPostId: UUID,
        val customerId: UUID,
        val videoCover: String,
        val videoName: String,
        val parentCategoryId: UUID,
        val categoryId: UUID?,
        val postType: Int,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val duration: Int,
    ) : RequestParam<Response>

    data class Response(
        val videoId: UUID,
    )

    private fun resolveFilePath(file: VideoFilePost): String? {
        return when {
            !file.encryptOutputPrefix.isNullOrBlank() -> file.encryptOutputPrefix
            !file.transcodeOutputPrefix.isNullOrBlank() -> file.transcodeOutputPrefix
            else -> null
        }
    }
}

