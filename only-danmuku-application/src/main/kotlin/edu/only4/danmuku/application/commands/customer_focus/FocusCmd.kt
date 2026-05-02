package edu.only4.danmuku.application.commands.customer_focus

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
import edu.only4.danmuku.application.validators.NotSelf
import edu.only4.danmuku.application.validators.UserExists
import edu.only4.danmuku.domain._share.meta.customer_focus.SCustomerFocus
import edu.only4.danmuku.domain.aggregates.customer_focus.factory.CustomerFocusFactory
import org.springframework.stereotype.Service

/**
 * 关注
 */
object FocusCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val exists = Mediator.repositories.find(
                SCustomerFocus.predicate { schema ->
                    schema.all(
                        schema.customerId eq request.userId,
                        schema.focusCustomerId eq request.userId
                    )
                }
            ).isNotEmpty()

            if (exists) return Response


            Mediator.factories.create(
                CustomerFocusFactory.Payload(
                    customerId = request.userId,
                    focusCustomerId = request.focusUserId
                )
            )
            Mediator.uow.save()
        
            return Response
        }
    }

    @NotSelf(userIdField = "userId", targetIdField = "focusUserId")
    @UserExists(targetIdField = "focusUserId")
    data class Request(
        val userId: UUID,
        val focusUserId: UUID,
    ) : RequestParam<Response>


    data object Response
}

