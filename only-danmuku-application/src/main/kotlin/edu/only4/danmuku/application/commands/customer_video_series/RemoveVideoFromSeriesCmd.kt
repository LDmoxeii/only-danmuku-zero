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
import edu.only4.danmuku.application.validators.SeriesOwnership
import edu.only4.danmuku.application.validators.VideoInSeries
import edu.only4.danmuku.domain._share.meta.customer_video_series.SCustomerVideoSeries
import org.springframework.stereotype.Service

/**
 * 从系列中移除视频
 */
object RemoveVideoFromSeriesCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val series = Mediator.repositories.findOne(
                SCustomerVideoSeries.predicateById(request.seriesId),
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频系列不存在：${request.seriesId}")

            val removed = series.removeVideo(request.videoId)
            if (removed) {
                Mediator.uow.save()
            }

            return Response(deleted = removed)
        }
    }

    @SeriesOwnership(seriesIdField = "seriesId", operatorIdField = "operatorId")
    @VideoInSeries(seriesIdField = "seriesId", videoIdField = "videoId")
    data class Request(
        /** 系列ID */
        val seriesId: Long,
        /** 视频ID */
        val videoId: Long,
        /** 操作用户ID */
        val operatorId: Long,
    ) : RequestParam<Response>

    data class Response(
        val deleted: Boolean,
    )
}
