package edu.only4.danmuku.domain.aggregates.video_post.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain._share.enums.PostType

import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

import org.springframework.stereotype.Service
import java.time.Instant

/**
 * 视频信息;
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/26
 */
@Service
@Aggregate(
    aggregate = "VideoPost",
    name = "VideoPostFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class VideoPostFactory : AggregateFactory<VideoPostFactory.Payload, VideoPost> {

    override fun create(entityPayload: Payload): VideoPost {
        return VideoPost(
            id = UUID(0L, 0L),
            videoCover = entityPayload.videoCover ?: "",
            videoName = entityPayload.videoName,
            customerId = entityPayload.customerId,
            pCategoryId = entityPayload.pCategoryId,
            categoryId = entityPayload.categoryId,
            status = entityPayload.status ?: VideoStatus.UNKNOW,
            postType = entityPayload.postType ?: (PostType.valueOfOrNull(1) ?: PostType.ORIGINAL),
            originInfo = entityPayload.originInfo,
            tags = entityPayload.tags,
            introduction = entityPayload.introduction,
            interaction = entityPayload.interaction,
            duration = 0,
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
         aggregate = "VideoPost",
         name = "VideoPostPayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
         val customerId: UUID,
         val videoName: String,
         val videoCover: String? = null,
         val pCategoryId: UUID,
         val categoryId: UUID? = null,
         val postType: PostType? = null,
         val originInfo: String? = null,
         val tags: String? = null,
         val introduction: String? = null,
         val interaction: String? = null,
         val status: VideoStatus? = null,
     ) : AggregatePayload<VideoPost>

}

