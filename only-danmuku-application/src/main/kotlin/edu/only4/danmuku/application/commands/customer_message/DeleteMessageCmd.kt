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
import edu.only4.danmuku.domain._share.meta.customer_message.SCustomerMessage
import org.springframework.stereotype.Service

/**
 * 删除消息
 */
object DeleteMessageCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 仅删除当前用户自己的指定消息（软删）
            Mediator.repositories.remove(
                SCustomerMessage.predicate { schema ->
                    schema.all(
                        schema.id eq request.messageId,
                        schema.customerId eq request.customerId,
                    )
                }
            )

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        /** 用户ID */
        val customerId: UUID,
        /** 消息ID */
        val messageId: UUID,
    ) : RequestParam<Response>

    data object Response
}

