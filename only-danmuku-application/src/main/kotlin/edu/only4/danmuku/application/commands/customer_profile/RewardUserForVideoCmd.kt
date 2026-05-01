package edu.only4.danmuku.application.commands.customer_profile

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
import edu.only4.danmuku.application._share.config.properties.SysSettingProperties
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

/**
 * 首次发布视频奖励硬币
 */
object RewardUserForVideoCmd {

    @Service
    class Handler(
        private val sysSettingProperties: SysSettingProperties,
    ) : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val profile = Mediator.repositories.findFirst(
                SCustomerProfile.predicate { it.userId eq request.customerId },
                persist = true
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "用户资料不存在: ")

            val rewardAmount = sysSettingProperties.postVideoCoinCount
            profile.rewardCoins(rewardAmount)

            Mediator.uow.save()
            return Response(rewarded = true, coinAmount = rewardAmount)
        }
    }

    data class Request(
        val customerId: Long,
    ) : RequestParam<Response>

    data class Response(
        val rewarded: Boolean,
        val coinAmount: Int,
    )
}
