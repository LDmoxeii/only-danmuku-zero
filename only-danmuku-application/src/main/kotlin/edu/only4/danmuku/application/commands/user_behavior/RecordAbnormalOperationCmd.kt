package edu.only4.danmuku.application.commands.user_behavior

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType
import org.springframework.stereotype.Service

object RecordAbnormalOperationCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                abnormalOperationLogId = TODO("set abnormalOperationLogId")
            )
        }
    }

    data class Request(
        val userId: Long,
        val userType: UserType,
        val opType: AbnormalOpType,
        val ip: String,
        val occurTime: Long?,
        val description: String?,
        val extra: String?
    ) : RequestParam<Response>

    data class Response(
        val abnormalOperationLogId: Long
    )

}
