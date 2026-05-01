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
import edu.only4.danmuku.domain._share.meta.video_post.SVideoPost
import edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType
import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend
import edu.only4.danmuku.domain.aggregates.customer_message.factory.CustomerMessageFactory
import org.springframework.stereotype.Service

/**
 * 发送视频审核不通过系统消息
 */
object SendVideoAuditFailedMessageCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val video = Mediator.repositories.findOne(
                SVideoPost.predicateById(request.videoId)
            ) ?: return Response

            val extend = UserMessageExtend(auditStatus = 5)
            Mediator.factories.create(
                CustomerMessageFactory.Payload(
                    customerId = video.customerId,
                    videoId = request.videoId,
                    messageType = MessageType.SYSTEM_MESSAGE,
                    sendSubjectId = request.operatorId,
                    extendJson = extend,
                )
            )
            Mediator.uow.save()
            return Response
        }
    }

    data class Request(
        val videoId: Long,
        val operatorId: Long? = null,
        val messageContent: String? = null,
    ) : RequestParam<Response>

    data object Response
}
