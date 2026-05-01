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
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

/** 从发送者向接收者转移硬币 */
object TransferCoinCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val sender = Mediator.repositories.findFirst(
                SCustomerProfile.predicate { it.userId eq request.senderUserId }
            ) ?: error("Sender profile not found: ${request.senderUserId}")
            val receiver = Mediator.repositories.findFirst(
                SCustomerProfile.predicate { it.userId eq request.receiverUserId }
            ) ?: error("Receiver profile not found: ${request.receiverUserId}")

            sender.transferCoin(receiver, request.amount)
            Mediator.uow.save()
            return Response()
        }
    }

    data class Request(
        val senderUserId: Long,
        val receiverUserId: Long,
        val amount: Int,
    ) : RequestParam<Response>

    class Response
}

