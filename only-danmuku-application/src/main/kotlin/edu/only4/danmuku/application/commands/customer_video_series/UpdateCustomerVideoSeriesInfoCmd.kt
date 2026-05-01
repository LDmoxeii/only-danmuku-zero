package edu.only4.danmuku.application.commands.customer_video_series

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
import edu.only4.danmuku.application.validators.UniqueSeriesNameForUser
import edu.only4.danmuku.domain._share.meta.customer_video_series.SCustomerVideoSeries
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.stereotype.Service

/**
 * 更新用户视频系列基础信息（名称、描述）
 */
object UpdateCustomerVideoSeriesInfoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val normalizedName = request.seriesName.trim()
            val normalizedDescription = request.seriesDescription?.trim()?.takeIf { it.isNotEmpty() }

            val series = Mediator.repositories.findFirst(
                SCustomerVideoSeries.predicateById(request.seriesId)
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "系列不存在: ${request.seriesId}")

            if (series.customerId != request.userId) {
                throw BusinessException(DanmukuBusinessErrors.OPERATION_FORBIDDEN, "没有权限操作该系列")
            }

            series.updateBasicInfo(normalizedName, normalizedDescription)
            Mediator.uow.save()
            return Response(seriesId = series.id)
        }
    }

    @UniqueSeriesNameForUser(userIdField = "userId", seriesIdField = "seriesId", seriesNameField = "seriesName")
    data class Request(
        val userId: Long,
        val seriesId: Long,
        @field:NotBlank(message = "系列名称不能为空")
        @field:Size(max = 100, message = "系列名称长度不能超过100")
        val seriesName: String,
        @field:Size(max = 200, message = "系列描述长度不能超过200")
        val seriesDescription: String? = null,
    ) : RequestParam<Response>

    data class Response(
        val seriesId: Long,
    )
}
