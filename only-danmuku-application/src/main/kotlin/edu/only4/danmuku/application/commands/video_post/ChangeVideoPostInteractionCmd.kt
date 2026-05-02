package edu.only4.danmuku.application.commands.video_post

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
import edu.only4.danmuku.domain._share.meta.video_post.SVideoPost
import org.springframework.stereotype.Service

object ChangeVideoPostInteractionCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {

            val videoPost = Mediator.repositories.findOne(
                SVideoPost.predicateById(request.videoPostId)
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频草稿不存在：${request.videoPostId}")

            if (videoPost.customerId != request.userId) {
                throw BusinessException(DanmukuBusinessErrors.OPERATION_FORBIDDEN, "无权限修改该视频互动设置")
            }

            videoPost.changeInteraction(request.interaction)

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        val videoPostId: UUID,
        val userId: UUID,
        val interaction: String?,
    ) : RequestParam<Response>

    data object Response
}

