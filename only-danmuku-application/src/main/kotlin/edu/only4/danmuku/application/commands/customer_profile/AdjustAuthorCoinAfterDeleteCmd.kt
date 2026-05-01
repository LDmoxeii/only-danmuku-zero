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

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.application._share.config.properties.SysSettingProperties
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

/**
 * 视频删除后回收对应用户硬币数
 */
object AdjustAuthorCoinAfterDeleteCmd {

    @Service
    class Handler(
        private val sysSettingProperties: SysSettingProperties,
    ) : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val deduction = sysSettingProperties.postVideoCoinCount
            if (deduction <= 0) {
                return Response(deducted = false, coinAmount = 0)
            }

            val profile = Mediator.repositories.findFirst(
                SCustomerProfile.predicate { it.userId eq request.authorId },
                persist = true
            ) ?: return Response(deducted = false, coinAmount = 0)

            profile.reclaimRewardCoins(deduction)
            Mediator.uow.save()

            return Response(deducted = true, coinAmount = deduction)
        }
    }

    data class Request(
        val authorId: Long,
    ) : RequestParam<Response>

    data class Response(
        val deducted: Boolean,
        val coinAmount: Int,
    )
}

