package edu.only4.danmuku.application.commands.video_post

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus
import org.springframework.stereotype.Service

object RecordVideoAuditTraceCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {
            Mediator.uow.save()

            return Response(
                traceId = TODO("set traceId")
            )
        }
    }

    data class Request(
        val videoPostId: Long,
        val auditStatus: AuditStatus,
        val reviewerId: Long?,
        val reviewerType: UserType,
        val reason: String?,
        val occurTime: Long?
    ) : RequestParam<Response>

    data class Response(
        val traceId: Long
    )

}
