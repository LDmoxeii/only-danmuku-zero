package edu.only4.danmuku.application.commands.video_comment

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
import edu.only4.danmuku.application.validators.CommentNotClosed
import edu.only4.danmuku.application.validators.ReplyCommentExists
import edu.only4.danmuku.application.validators.VideoExists
import edu.only4.danmuku.domain._share.meta.video.SVideo
import edu.only4.danmuku.domain.aggregates.video_comment.factory.VideoCommentFactory
import org.springframework.stereotype.Service

/**
 * 发表评论
 */
object PostCommentCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 查找视频，获取视频作者ID
            val video = Mediator.repositories.findOne(
                SVideo.predicateById(request.videoId),
                persist = false
            ) ?: throw BusinessException(DanmukuBusinessErrors.RESOURCE_NOT_FOUND, "视频不存在：${request.videoId}")

            val parentId = request.replyCommentId ?: 0L
            val now = System.currentTimeMillis() / 1000

            val comment = Mediator.factories.create(
                VideoCommentFactory.Payload(
                    parentId = parentId,
                    videoId = request.videoId,
                    videoOwnerId = video.customerId,
                    content = request.content,
                    imgPath = request.imgPath,
                    customerId = request.customerId,
                    replyCustomerId = request.replyCustomerId,
                    postTime = now
                )
            )

            Mediator.uow.save()

            return Response(commentId = comment.id)
        }
    }

    @ReplyCommentExists(videoIdField = "videoId", replyCommentIdField = "replyCommentId")
    data class Request(
        @field:VideoExists
        @field:CommentNotClosed
        val videoId: Long,
        val replyCommentId: Long? = null,
        val customerId: Long,
        val replyCustomerId: Long,
        val content: String,
        val imgPath: String? = null,
    ) : RequestParam<Response>

    data class Response(
        val commentId: Long
    )
}
