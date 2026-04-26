package edu.only4.danmuku.application.commands.customer_profile

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import org.springframework.stereotype.Service

object UpdateCustomerProfileCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        val customerId: Long,
        val nickName: String?,
        val avatar: String?,
        val sex: Int?,
        val birthday: String?,
        val school: String?,
        val personIntroduction: String?,
        val noticeInfo: String?,
        val theme: Int?
    ) : RequestParam<Response>

    data object Response

}
