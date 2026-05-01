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
import edu.only4.danmuku.application.validators.CommentExists
import edu.only4.danmuku.application.validators.VideoExists
import edu.only4.danmuku.domain._share.meta.customer_action.SCustomerAction
import edu.only4.danmuku.domain._share.meta.video_comment.SVideoComment
import edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType
import edu.only4.danmuku.domain.aggregates.customer_action.factory.CustomerActionFactory
import org.springframework.stereotype.Service

/**
 * 点踩评论（与点赞互斥，支持取消）
 */
object DislikeCommentCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val hateActions = Mediator.repositories.find(
                SCustomerAction.predicate { s ->
                    s.all(
                        s.customerId eq request.customerId,
                        s.videoId eq request.videoId,
                        s.commentId eq request.commentId,
                        s.actionType eq ActionType.HATE_COMMENT.value
                    )
                },
                persist = false
            )

            val likeActions = Mediator.repositories.find(
                SCustomerAction.predicate { s ->
                    s.all(
                        s.customerId eq request.customerId,
                        s.videoId eq request.videoId,
                        s.commentId eq request.commentId,
                        s.actionType eq ActionType.LIKE_COMMENT.value
                    )
                },
                persist = false
            )

            val comment = Mediator.repositories.findOne(
                SVideoComment.predicateById(request.commentId),
                persist = false
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "评论不存在")

            var isCancel = false
            var hadOpposite = false

            if (hateActions.isNotEmpty()) {
                hateActions.forEach(Mediator.uow::remove)
                isCancel = true
            } else {
                if (likeActions.isNotEmpty()) {
                    likeActions.forEach(Mediator.uow::remove)
                    hadOpposite = true
                }

                Mediator.factories.create(
                    CustomerActionFactory.Payload(
                        customerId = request.customerId,
                        videoId = request.videoId,
                        videoOwnerId = comment.videoOwnerId,
                        commentId = request.commentId,
                        actionType = ActionType.HATE_COMMENT,
                        actionCount = 1
                    )
                )
            }

            Mediator.uow.save()
            return Response(isCancel = isCancel, hadOpposite = hadOpposite)
        }
    }

    data class Request(
        @field:VideoExists
        val videoId: Long,
        @field:CommentExists
        val commentId: Long,
        val customerId: Long,
    ) : RequestParam<Response>

    data class Response(
        val isCancel: Boolean,
        val hadOpposite: Boolean,
    )
}

