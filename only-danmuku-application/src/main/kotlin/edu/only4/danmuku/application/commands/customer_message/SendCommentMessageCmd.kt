package edu.only4.danmuku.application.commands.customer_message

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
import edu.only4.danmuku.domain._share.meta.video.SVideo
import edu.only4.danmuku.domain.aggregates.customer_message.factory.CustomerMessageFactory
import edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType
import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend
import org.springframework.stereotype.Service

/**
 * 发送评论消息（顶级评论 → 发给视频作者）
 */
object SendCommentMessageCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val video = Mediator.repositories.findOne(
                SVideo.predicateById(request.videoId)
            ) ?: return Response()

            val receiverId = video.customerId
            if (receiverId == request.sendUserId) {
                return Response()
            }

            val extend = UserMessageExtend(messageContent = request.content)

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
            return Response()
        }

        // no-op
    }

    data class Request(
        val videoId: Long,
        val sendUserId: Long,
        val content: String?,
    ) : RequestParam<Response>

    class Response
}
