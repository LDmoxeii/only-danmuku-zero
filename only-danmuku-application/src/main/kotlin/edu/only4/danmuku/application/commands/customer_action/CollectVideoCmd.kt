package edu.only4.danmuku.application.commands.customer_action

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
import edu.only4.danmuku.application.validators.VideoExists
import edu.only4.danmuku.domain._share.meta.customer_action.SCustomerAction
import edu.only4.danmuku.domain._share.meta.video.SVideo
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType
import edu.only4.danmuku.domain.aggregates.customer_action.factory.CustomerActionFactory
import org.springframework.stereotype.Service

/**
 * 收藏视频（Toggle 逻辑）
 */
object CollectVideoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 查询已有收藏记录
            val existingAction = Mediator.repositories.find(
                SCustomerAction.predicate { schema ->
                    schema.all(
                        schema.customerId eq request.customerId,
                        schema.videoId eq request.videoId,
                        schema.actionType eq ActionType.FAVORITE_VIDEO.value
                    )
                },
                persist = false
            )

            val isCancel: Boolean

            if (existingAction.isNotEmpty()) {
                existingAction.forEach(Mediator.uow::remove)

                isCancel = true
            } else {
                // 查询视频信息
                val video = Mediator.repositories.findOne(
                    SVideo.predicateById(request.videoId),
                    persist = false
                ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频不存在")

                Mediator.factories.create(
                    CustomerActionFactory.Payload(
                        customerId = request.customerId,
                        videoId = request.videoId,
                        videoOwnerId = video.customerId,
                        commentId = 0L,
                        actionType = ActionType.FAVORITE_VIDEO,
                        actionCount = 1
                    )
                )

                isCancel = false
            }

            Mediator.uow.save()

            return Response(isCancel = isCancel)
        }
    }

    data class Request(
        @field:VideoExists
        val videoId: Long,
        val customerId: Long
    ) : RequestParam<Response>

    data class Response(
        /** 是否为取消操作 */
        val isCancel: Boolean
    )
}
