package edu.only4.danmuku.application.commands.user_behavior

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult
import edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType
import org.springframework.stereotype.Service

object RecordLoginLogCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                logId = TODO("set logId")
            )
        }
    }

    data class Request(
        val userId: Long?,
        val userType: UserType,
        val loginName: String,
        val loginType: LoginType,
        val result: LoginResult,
        val ip: String,
        val userAgent: String?,
        val reason: String?,
        val occurTime: Long?
    ) : RequestParam<Response>

    data class Response(
        val logId: Long
    )

}
