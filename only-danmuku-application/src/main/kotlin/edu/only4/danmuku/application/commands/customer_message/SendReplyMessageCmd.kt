package edu.only4.danmuku.application.commands.customer_message

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

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.meta.video_comment.SVideoComment
import edu.only4.danmuku.domain.aggregates.customer_message.factory.CustomerMessageFactory
import edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType
import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend
import org.springframework.stereotype.Service

/**
 * 发送评论回复消息（回复 → 发给被回复评论作者）
 */
object SendReplyMessageCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val parent = Mediator.repositories.findOne(
                SVideoComment.predicateById(request.replyCommentId)
            ) ?: return Response

            val receiverId = parent.customerId
            if (receiverId == request.sendUserId) {
                return Response
            }

            val extend = UserMessageExtend(
                messageContent = request.content,
                messageContentReply = request.replyCommentContent
            )

            Mediator.factories.create(
                CustomerMessageFactory.Payload(
                    customerId = receiverId,
                    videoId = request.videoId,
                    messageType = MessageType.COMMENT_MENTION,
                    sendSubjectId = request.sendUserId,
                    extendJson = extend,
                )
            )
            Mediator.uow.save()
            return Response
        }

        // no-op
    }

    data class Request(
        val videoId: UUID,
        val sendUserId: UUID,
        val content: String?,
        val replyCommentId: UUID,
        val replyCommentContent: String?,
    ) : RequestParam<Response>

    data object Response
}

