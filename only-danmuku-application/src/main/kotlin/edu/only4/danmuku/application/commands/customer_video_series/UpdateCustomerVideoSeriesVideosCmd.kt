package edu.only4.danmuku.application.commands.customer_video_series

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
import edu.only4.danmuku.domain._share.meta.customer_video_series.SCustomerVideoSeries
import edu.only4.danmuku.application.validators.VideoIdsBelongToUser
import edu.only4.danmuku.application.validators.SeriesVideoCountLimit
import org.springframework.stereotype.Service
import java.util.LinkedHashSet

/**
 * 更新用户视频系列视频
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object UpdateCustomerVideoSeriesVideosCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val series = Mediator.repositories.findFirst(
                SCustomerVideoSeries.predicateById(request.seriesId)
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "系列不存在: ${request.seriesId}")

            if (series.customerId != request.userId) {
                throw BusinessException(DanmukuBusinessErrors.OPERATION_FORBIDDEN, "没有权限操作该系列")
            }

            val incomingVideoIds = parseVideoIds(request.videoIds)
            if (incomingVideoIds.isEmpty()) {
                return Response
            }

            val currentVideoIds = series.videos
                .sortedBy { it.sort }
                .map { it.videoId }

            val updatedVideoIds = if (request.isDelete) {
                val removalSet = incomingVideoIds.toSet()
                val filtered = currentVideoIds.filterNot(removalSet::contains)
                if (filtered.size == currentVideoIds.size) {
                    return Response
                }
                filtered
            } else {
                val currentSet = currentVideoIds.toSet()
                val incomingSet = incomingVideoIds.toSet()

                if (incomingSet == currentSet && incomingVideoIds.size == currentVideoIds.size) {
                    incomingVideoIds
                } else {
                    val additions = incomingVideoIds.filterNot(currentSet::contains)
                    currentVideoIds + additions
                }
            }

            series.replaceVideos(request.userId, updatedVideoIds)
            Mediator.uow.save()

            return Response
        }

        private fun parseVideoIds(rawVideoIds: String?): List<UUID> {
            val sanitized = rawVideoIds?.trim() ?: return emptyList()
            if (sanitized.isEmpty()) {
                return emptyList()
            }
            val normalized = sanitized
                .removePrefix("[")
                .removeSuffix("]")
                .replace("\n", ",")
                .replace("\r", ",")
                .replace("\t", ",")
            val result = mutableListOf<UUID>()
            val dedupe = LinkedHashSet<UUID>()
            normalized.split(',', '，').forEach { item ->
                val trimmed = item.trim().trim('"')
                if (trimmed.isEmpty()) {
                    return@forEach
                }
                val id = runCatching { UUID.fromString(trimmed) }
                    .getOrElse { throw RequestException(CommonErrors.PARAM_INVALID, "无效的视频ID: $trimmed") }
                if (dedupe.add(id)) {
                    result.add(id)
                }
            }
            return result
        }
    }

    @VideoIdsBelongToUser(userIdField = "userId", videoIdsField = "videoIds")
    @SeriesVideoCountLimit(videoIdsField = "videoIds")
    data class Request(
        /** 用户ID */
        val userId: UUID,
        /** 系列ID */
        val seriesId: UUID,
        /** 视频ID列表(逗号分隔) - 可用于添加或删除 */
        val videoIds: String? = null,
        /** 是否删除操作 */
        val isDelete: Boolean = false
    ) : RequestParam<Response>

    data object Response
}

