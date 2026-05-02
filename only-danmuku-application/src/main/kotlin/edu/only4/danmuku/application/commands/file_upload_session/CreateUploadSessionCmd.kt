package edu.only4.danmuku.application.commands.file_upload_session

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
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.factory.VideoFileUploadSessionFactory
import jakarta.validation.constraints.Positive
import org.springframework.stereotype.Service
import java.time.Instant

/**
 * 创建视频分片上传会话（预上传）
 */
object CreateUploadSessionCmd {

    @Service
    class Handler() : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val now = Instant.now().epochSecond
            val expiresAt = now + 24 * 60 * 60 // 24 hours

            val session = Mediator.factories.create(
                VideoFileUploadSessionFactory.Payload(
                    customerId = request.customerId,
                    fileName = request.fileName,
                    chunks = request.chunks,
                    expiresAt = expiresAt,
                )
            )

            Mediator.uow.save()

            return Response(uploadId = session.id)
        }
    }

    data class Request(
        val customerId: UUID,
        val fileName: String,
        @param:Positive
        val chunks: Int,
    ) : RequestParam<Response>

    data class Response(
        val uploadId: UUID,
    )
}

