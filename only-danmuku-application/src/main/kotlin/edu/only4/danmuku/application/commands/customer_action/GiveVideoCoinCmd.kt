package edu.only4.danmuku.application.commands.customer_action

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
import edu.only4.danmuku.application.validators.NotSelfCoin
import edu.only4.danmuku.application.validators.SufficientCoinBalance
import edu.only4.danmuku.application.validators.UniqueCustomerActionType
import edu.only4.danmuku.application.validators.VideoExists
import edu.only4.danmuku.domain._share.meta.video.SVideo
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType
import edu.only4.danmuku.domain.aggregates.customer_action.factory.CustomerActionFactory
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.stereotype.Service

/**
 * 视频投币（一次性操作，不可撤销）
 * - 验证：不能给自己投币
 * - 验证：同一视频只能投一次
 * - 验证：硬币余额充足
 */
object GiveVideoCoinCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 查询视频信息
            val video = Mediator.repositories.findOne(
                SVideo.predicateById(request.videoId),
                persist = false
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频不存在")

            // 创建投币记录
            Mediator.factories.create(
                CustomerActionFactory.Payload(
                    customerId = request.customerId,
                    videoId = request.videoId,
                    videoOwnerId = video.customerId,
                    commentId = UUID(0L, 0L),
                    actionType = ActionType.COIN_VIDEO,
                    actionCount = request.coinCount
                )
            )

            Mediator.uow.save()

            return Response(coinCount = request.coinCount)
        }
    }

    @NotSelfCoin(userIdField = "customerId", videoIdField = "videoId")
    @UniqueCustomerActionType(message = "该视频已投过币")
    @SufficientCoinBalance(userIdField = "customerId", coinCountField = "coinCount")
    data class Request(
        @field:VideoExists
        val videoId: UUID,
        val customerId: UUID,
        @field:Min(1, message = "投币数量至少为1")
        @field:Max(2, message = "投币数量最多为2")
        val coinCount: Int,
        val actionType: ActionType = ActionType.COIN_VIDEO
    ) : RequestParam<Response>

    data class Response(
        /** 投币数量 */
        val coinCount: Int
    )
}

