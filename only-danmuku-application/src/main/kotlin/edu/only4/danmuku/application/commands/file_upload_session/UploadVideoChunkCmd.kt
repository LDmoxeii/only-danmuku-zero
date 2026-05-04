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

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.application.validators.ValidateUploadChunk
import edu.only4.danmuku.domain._share.meta.video_file_upload_session.SVideoFileUploadSession
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import org.springframework.stereotype.Service
import java.time.Instant

/**
 * 上传视频分片命令
 */
object UploadVideoChunkCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val uploadId = request.uploadId

            // 加载会话
            val session: VideoFileUploadSession = Mediator.repositories.findFirst(
                SVideoFileUploadSession.predicateById(uploadId)
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "文件不存在请重新上传")

            // 归属与可用性检查（聚合内部方法）
            session.ensureOwnedBy(request.customerId)
            session.ensureActive()
            session.ensureChunkAllowed(request.chunkIndex)

            // 推进聚合状态
            val now = Instant.now().epochSecond
            session.onChunkUploaded(request.chunkIndex, request.chunkSize, now)
            session.tryMarkDoneIfComplete()

            Mediator.uow.save()
        
            return Response
        }
    }

    @ValidateUploadChunk
    data class Request(
        val customerId: UUID,
        val uploadId: UUID,
        val chunkIndex: Int,
        val chunkSize: Long,
    ) : RequestParam<Response>

    data object Response
}


