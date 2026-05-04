package edu.only4.danmuku.application.commands.video_post

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

import edu.only4.danmuku.domain._share.enums.UserType
import edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus
import edu.only4.danmuku.domain.aggregates.video_audit_trace.factory.VideoAuditTraceFactory

import org.springframework.stereotype.Service

/**
 * 记录视频审核追溯日志
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/11/21
 */
object RecordVideoAuditTraceCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val occurTime = request.occurTime ?: (System.currentTimeMillis() / 1000L)
            val trace = Mediator.factories.create(
                VideoAuditTraceFactory.Payload(
                    videoPostId = request.videoPostId,
                    auditStatus = request.auditStatus,
                    reviewerId = request.reviewerId,
                    reviewerType = request.reviewerType,
                    reason = request.reason,
                    occurTime = occurTime
                )
            )

            Mediator.uow.save()

            return Response(
                traceId = trace.id
            )
        }

    }

    data class Request(
        val videoPostId: UUID,
        val auditStatus: AuditStatus,
        val reviewerId: UUID?,
        val reviewerType: UserType,
        val reason: String? = null,
        val occurTime: Long? = null,
    ) : RequestParam<Response>

    data class Response(
        val traceId: UUID
    )
}

