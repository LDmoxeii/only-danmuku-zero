package edu.only4.danmuku.application.commands.video_post_processing

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
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessing

import org.springframework.stereotype.Service

/**
 * 回写单个分P加密结果（处理聚合内变更）
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2026/01/05
 */
object ApplyVideoPostProcessingEncryptResultCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val processing = Mediator.repositories.findFirst(
                SVideoPostProcessing.predicate { schema ->
                    schema.videoPostId.eq(request.videoPostId)
                }
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "处理聚合不存在: ${request.videoPostId}")

            val outputPrefix = request.encryptedPrefix?.takeIf { it.isNotBlank() }
                ?: resolveOutputPrefix(request.encryptedMasterPath)
            processing.applyEncryptResult(
                fileIndex = request.fileIndex,
                success = request.success,
                encryptedPrefix = if (request.success) outputPrefix else null,
                failReason = request.failReason
            )

            Mediator.uow.save()

            return Response(
                success = request.success,
                failReason = request.failReason
            )
        }

    }

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val success: Boolean,
        val encryptMethod: String = "HLS_AES_128",
        val keyVersion: Int,
        val encryptedPrefix: String? = null,
        val encryptedMasterPath: String? = null,
        val encryptedVariants: String? = null,
        val failReason: String?
    ) : RequestParam<Response>

    data class Response(
        val success: Boolean = true,
        val failReason: String?
    )

    private fun resolveOutputPrefix(masterPath: String?): String? {
        if (masterPath.isNullOrBlank()) return null
        val trimmed = masterPath.trim()
        return if (trimmed.endsWith("/master.m3u8")) {
            trimmed.removeSuffix("/master.m3u8")
        } else {
            trimmed.substringBeforeLast("/", trimmed)
        }.trimEnd('/')
    }
}
