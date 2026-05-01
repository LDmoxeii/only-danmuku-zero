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
import edu.only4.danmuku.domain._share.meta.customer_message.SCustomerMessage
import edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType
import edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType
import org.springframework.stereotype.Service

/**
 * 标记所有消息为已读
 */
object MarkAllAsReadCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val msgTypeEnum = MessageType.valueOfOrNull(request.messageType)

            val messages = Mediator.repositories.find(
                SCustomerMessage.predicate { schema ->
                    schema.allNotNull(
                        schema.customerId eq request.customerId,
                        schema.readType eq ReadType.UNREAD,
                        schema.messageType `eq?` msgTypeEnum
                    )!!
                }
            )

            if (messages.isEmpty()) {
                return Response
            }

            val now = System.currentTimeMillis() / 1000
            messages.forEach { it.markAsRead(now) }

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        val customerId: Long,
        val messageType: Int? = null,
    ) : RequestParam<Response>

    data object Response
}
