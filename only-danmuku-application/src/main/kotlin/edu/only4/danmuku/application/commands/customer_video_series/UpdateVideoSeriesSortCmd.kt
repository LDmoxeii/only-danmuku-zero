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
import edu.only4.danmuku.application.validators.SeriesBelongToUser
import edu.only4.danmuku.domain._share.meta.customer_video_series.SCustomerVideoSeries
import jakarta.validation.constraints.NotEmpty
import org.springframework.stereotype.Service

/**
 * 更新用户视频系列排序
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object UpdateVideoSeriesSortCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val seriesList = Mediator.repositories.find(
                SCustomerVideoSeries.predicateByIds(request.seriesIds)
            )

            if (seriesList.size != request.seriesIds.toSet().size) {
                throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "部分系列不存在")
            }

            val byId = seriesList.associateBy { it.id }

            var sortNo = 1
            request.seriesIds.forEach { seriesId ->
                val series = byId[seriesId]
                    ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "系列不存在：$seriesId")
                series.updateSort(sortNo)
                sortNo += 1
            }

            Mediator.uow.save()

            return Response
        }
    }

    @SeriesBelongToUser(userIdField = "userId", seriesIdsField = "seriesIds")
    data class Request(
        /** 用户ID */
        val userId: UUID,

        /** 系列ID列表（按新的排序顺序） */
        @field:NotEmpty(message = "系列ID列表不能为空")
        val seriesIds: List<UUID>
    ) : RequestParam<Response>

    data object Response
}

