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

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors
import com.only.engine.json.misc.JsonUtils
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessing
import edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingVariant

import org.springframework.stereotype.Service

/**
 * 回写单个分P转码结果（处理聚合内变更）
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2026/01/05
 */
object ApplyVideoPostProcessingTranscodeResultCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val processing = Mediator.repositories.findFirst(
                SVideoPostProcessing.predicate { schema ->
                    schema.videoPostId.eq(request.videoPostId)
                }
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "处理聚合不存在: ${request.videoPostId}")

            val variants = parseVariants(request.variantsJson)
            processing.applyTranscodeResult(
                fileIndex = request.fileIndex,
                success = request.success,
                outputPrefix = request.outputPrefix,
                outputPath = request.outputPath,
                duration = request.duration,
                fileSize = request.fileSize,
                variantsJson = request.variantsJson,
                failReason = request.failReason,
                variants = variants
            )

            Mediator.uow.save()

            return Response(
                success = request.success,
                failReason = request.failReason
            )
        }

    }

    data class Request(
        val videoPostId: UUID,
        val fileIndex: Int,
        val success: Boolean,
        val outputPrefix: String?,
        val outputPath: String?,
        val duration: Int?,
        val fileSize: Long?,
        val variantsJson: String?,
        val failReason: String?
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val failReason: String?
    )

    private fun parseVariants(variantsJson: String?): List<VideoPostProcessingVariant> {
        if (variantsJson.isNullOrBlank()) return emptyList()
        val payloads = JsonUtils.parseArray(variantsJson, VariantPayload::class.java)
        if (payloads.isEmpty()) return emptyList()
        return payloads.map { payload ->
            VideoPostProcessingVariant(
                id = UUID(0L, 0L),
                quality = payload.quality,
                width = payload.width,
                height = payload.height,
                videoBitrateKbps = payload.videoBitrateKbps,
                audioBitrateKbps = payload.audioBitrateKbps,
                bandwidthBps = payload.bandwidthBps,
                playlistPath = payload.playlistPath,
                segmentPrefix = payload.segmentPrefix,
                segmentDuration = payload.segmentDuration,
                transcodeStatus = ProcessStatus.SUCCESS,
                encryptStatus = ProcessStatus.PENDING,
                encryptFailReason = null,
                createUserId = null,
                createBy = null,
                createTime = null,
                updateUserId = null,
                updateBy = null,
                updateTime = null,
                deleted = 0L,
            )
        }
    }

    data class VariantPayload(
        val quality: String = "",
        val width: Int = 0,
        val height: Int = 0,
        val videoBitrateKbps: Int = 0,
        val audioBitrateKbps: Int = 0,
        val bandwidthBps: Int = 0,
        val playlistPath: String = "",
        val segmentPrefix: String? = null,
        val segmentDuration: Int? = null,
    )
}

