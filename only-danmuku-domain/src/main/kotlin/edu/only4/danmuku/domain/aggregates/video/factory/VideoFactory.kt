package edu.only4.danmuku.domain.aggregates.video.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video.Video
import edu.only4.danmuku.domain.aggregates.video.VideoSyncFileArgs
import edu.only4.danmuku.domain.aggregates.video.syncFromBasics
import org.springframework.stereotype.Service

/**
 * 使用基础字段创建 Video 的工厂（不依赖 VideoPost 聚合）。
 */
@Service
@Aggregate(
    aggregate = "Video",
    name = "VideoBasicsFactory",
    type = Aggregate.TYPE_FACTORY,
    description = "根据基础字段创建成品视频"
)
class VideoFactory : AggregateFactory<VideoFactory.Payload, Video> {

    override fun create(entityPayload: Payload): Video {
        return Video(
            id = UUID(0L, 0L),
            videoPostId = UUID(0L, 0L),
            customerId = UUID(0L, 0L),
            videoCover = "",
            videoName = "",
            pCategoryId = UUID(0L, 0L),
            categoryId = null,
            postType = edu.only4.danmuku.domain._share.enums.PostType.UNKNOW,
            originInfo = null,
            tags = null,
            introduction = null,
            interaction = null,
            duration = 0,
            playCount = 0,
            likeCount = 0,
            danmukuCount = 0,
            commentCount = 0,
            coinCount = 0,
            collectCount = 0,
            recommendType = edu.only4.danmuku.domain.aggregates.video.enums.RecommendType.UNKNOW,
            lastPlayTime = null,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L
        ).apply {
            syncFromBasics(
                videoPostId = entityPayload.videoPostId,
                customerId = entityPayload.customerId,
                videoCover = entityPayload.videoCover,
                videoName = entityPayload.videoName,
                parentCategoryId = entityPayload.parentCategoryId,
                categoryId = entityPayload.categoryId,
                postType = entityPayload.postType,
                originInfo = entityPayload.originInfo,
                tags = entityPayload.tags,
                introduction = entityPayload.introduction,
                interaction = entityPayload.interaction,
                duration = entityPayload.duration,
                files = entityPayload.files
            )
        }
    }

    @Aggregate(
        aggregate = "Video",
        name = "VideoBasicsFactoryPayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = "创建成品视频的基础字段载荷"
    )
    data class Payload(
        val videoPostId: UUID,
        val customerId: UUID,
        val videoCover: String,
        val videoName: String,
        val parentCategoryId: UUID,
        val categoryId: UUID?,
        val postType: Int,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val duration: Int,
        val files: List<VideoSyncFileArgs>,
    ) : AggregatePayload<Video>
}


