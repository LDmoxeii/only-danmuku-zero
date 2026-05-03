package edu.only4.danmuku.application.commands.user

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
import edu.only4.danmuku.application.validators.user.unique.UniqueUserEmail
import edu.only4.danmuku.application.validators.UniqueUserNickname
import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.user.factory.UserFactory
import org.springframework.stereotype.Service

/**
 * 注册帐号
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object RegisterAccountCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            Mediator.factories.create(
                UserFactory.Payload(
                    type = UserType.SYS_USER,
                    email = request.email,
                    nickName = request.nickName,
                    registerPassword = request.registerPassword
                )
            )
            Mediator.uow.save()
        
            return Response
        }
    }

    @UniqueUserEmail
    @UniqueUserNickname
    class Request(
        val email: String,
        val nickName: String,
        val registerPassword: String,
    ) : RequestParam<Response>

    data object Response
}
