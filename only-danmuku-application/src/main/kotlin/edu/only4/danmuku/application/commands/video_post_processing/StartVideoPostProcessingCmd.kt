package edu.only4.danmuku.application.commands.video_post_processing

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
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessing
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingAppendFileSpec
import edu.only4.danmuku.domain.aggregates.video_post_processing.factory.VideoPostProcessingFactory
import org.springframework.stereotype.Service
import java.nio.file.Paths
import java.util.*

/**
 * 初始化稿件处理聚合（文件清单）
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2026/01/05
 */
object StartVideoPostProcessingCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val filePayloads = request.fileList.map { spec ->
                val outputDir = resolveOutputDir(request.videoPostId, spec.fileIndex)
                val objectPrefix = resolveObjectPrefix(request.videoPostId, spec.fileIndex)
                val encOutputDir = "${objectPrefix.trimEnd('/')}/enc"
                VideoPostProcessingAppendFileSpec(
                    uploadId = spec.uploadId,
                    fileIndex = spec.fileIndex,
                    transcodeOutputPath = outputDir,
                    transcodeOutputPrefix = objectPrefix,
                    encryptOutputDir = encOutputDir,
                    duration = spec.duration,
                    fileSize = spec.fileSize
                )
            }
            val processing = Mediator.repositories.findOne(
                SVideoPostProcessing.predicate { schema ->
                    schema.videoPostId.eq(request.videoPostId)
                }
            ) ?: Mediator.factories.create(
                VideoPostProcessingFactory.Payload(
                    videoPostId = request.videoPostId,
                    fileSize = request.fileList.sumOf { it.fileSize ?: 0 }.toInt()
                )
            )

            processing.appendFiles(filePayloads)
            Mediator.uow.save()

            return Response
        }

    }

    data class Request(
        val videoPostId: UUID,
        val fileList: List<VideoPostProcessingFileSpec>
    ) : RequestParam<Response>

    data class VideoPostProcessingFileSpec(
        val uploadId: UUID,
        val fileIndex: Int,
        val fileName: String?,
        val fileSize: Long?,
        val duration: Int?
    )

    data object Response

    private fun resolveOutputDir(videoPostId: UUID, fileIndex: Int): String {
        val base = System.getProperty("java.io.tmpdir").trimEnd('/', '\\')
        val token = UUID.randomUUID().toString().replace("-", "")
        return Paths.get(base, "vpp", videoPostId.toString(), fileIndex.toString(), token).toString()
    }

    private fun resolveObjectPrefix(videoPostId: UUID, fileIndex: Int): String {
        val token = UUID.randomUUID().toString().replace("-", "")
        return "video-post/${videoPostId}/${fileIndex}/${token}"
    }
}

