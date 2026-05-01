package edu.only4.danmuku.application.commands.file_upload_session

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
import edu.only4.danmuku.application.validators.ValidateDeleteUploadSession
import edu.only4.danmuku.domain._share.meta.video_file_upload_session.SVideoFileUploadSession
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import org.springframework.stereotype.Service
import java.time.Instant

/**
 * 删除上传中的视频（终止并清理会话）
 */
object DeleteUploadSessionCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val uploadId = request.uploadId

            val session: VideoFileUploadSession = Mediator.repositories.findOne(
                SVideoFileUploadSession.predicateById(uploadId),
                persist = false
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "文件不存在请重新上传")

            session.ensureOwnedBy(request.customerId)

            val tempPath = session.tempDir?.trim().orEmpty()

            // 终止并软删除会话
            val now = Instant.now().epochSecond
            session.abort(now)

            Mediator.uow.remove(session)

            return Response(
                tempPath = tempPath.ifBlank { null }
            )
        }
    }

    @ValidateDeleteUploadSession
    data class Request(
        val customerId: Long,
        val uploadId: Long,
    ) : RequestParam<Response>

    data class Response(
        val tempPath: String?
    )
}

