package edu.only4.danmuku.application.commands.video_comment

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
import edu.only4.danmuku.application.validators.CommentDeletePermission
import edu.only4.danmuku.application.validators.CommentExists
import edu.only4.danmuku.domain._share.meta.video_comment.SVideoComment
import org.springframework.stereotype.Service

/**
 * 删除评论
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object DeleteVideoCommentCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val comment = Mediator.repositories.findOne(
                SVideoComment.predicateById(request.commentId),
                persist = false
            ) ?: return Response

            Mediator.uow.remove(comment)

            if (comment.parentId == UUID(0L, 0L)) {
                Mediator.repositories.remove(
                    SVideoComment.predicate { schema ->
                        schema.parentId eq comment.id
                    }
                )
            }

            Mediator.uow.save()
        
            return Response
        }
    }

    @CommentDeletePermission
    data class Request(
        @field:CommentExists
        val commentId: UUID,
        val operatorId: UUID? = null,
    ) : RequestParam<Response>

    data object Response
}

