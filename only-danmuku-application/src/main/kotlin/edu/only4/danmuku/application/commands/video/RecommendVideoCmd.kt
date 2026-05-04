package edu.only4.danmuku.application.commands.video

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
import edu.only4.danmuku.domain._share.meta.video.SVideo
import org.springframework.stereotype.Service

/**
 * 切换视频推荐状态
 */
object RecommendVideoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val video = Mediator.repositories.findOne(
                SVideo.predicate { it.videoPostId eq request.videoId }
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频不存在：${request.videoId}")

            // 切换推荐状态（推荐 <-> 未推荐）
            video.toggleRecommend()

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        val videoId: UUID
    ) : RequestParam<Response>

    data object Response
}

