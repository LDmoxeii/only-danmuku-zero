package edu.only4.danmuku.domain.aggregates.video_comment.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate

import edu.only4.danmuku.domain.aggregates.video_comment.VideoComment

import org.springframework.stereotype.Service

/**
 * 评论;
 */
@Service
@Aggregate(
    aggregate = "VideoComment",
    name = "VideoCommentFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoCommentFactory : AggregateFactory<VideoCommentFactory.Payload, VideoComment> {

    override fun create(entityPayload: Payload): VideoComment {
        return VideoComment(
            id = UUID(0L, 0L),
            parentId = entityPayload.parentId,
            videoId = entityPayload.videoId,
            videoOwnerId = entityPayload.videoOwnerId,
            content = entityPayload.content,
            imgPath = entityPayload.imgPath,
            customerId = entityPayload.customerId,
            replyCustomerId = entityPayload.replyCustomerId,
            topType = 0,
            postTime = entityPayload.postTime,
            likeCount = 0,
            hateCount = 0,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L
        )
    }

     @Aggregate(
        aggregate = "VideoComment",
        name = "VideoCommentPayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
         val parentId: UUID = UUID(0L, 0L),
         val videoId: UUID,
         val videoOwnerId: UUID,
         val content: String,
         val imgPath: String?,
         val customerId: UUID,
         val replyCustomerId: UUID?,
         val postTime: Long,
    ) : AggregatePayload<VideoComment>

}


