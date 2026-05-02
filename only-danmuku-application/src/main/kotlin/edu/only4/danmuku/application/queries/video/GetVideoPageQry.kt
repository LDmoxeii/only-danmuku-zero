
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.domain._share.enums.PostType
import edu.only4.danmuku.domain.aggregates.video.enums.RecommendType
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var userId: UUID? = null,
        var categoryParentId: UUID? = null,
        var categoryId: UUID? = null,
        var videoNameFuzzy: String? = null,
        var recommendType: RecommendType? = null,
        var excludeVideoIds: List<UUID>? = null
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<VideoItem>
    ) {
        data class VideoItem(
            val videoId: UUID,
            val videoCover: String?,
            val videoName: String?,
            val userId: UUID?,
            val createTime: Long,
            val lastUpdateTime: Long?,
            val parentCategoryId: UUID,
            val categoryId: UUID?,
            val postType: PostType,
            val originInfo: String?,
            val tags: String?,
            val introduction: String?,
            val duration: Int,
            val status: VideoStatus,
            val playCount: Int,
            val likeCount: Int,
            val danmukuCount: Int,
            val commentCount: Int,
            val coinCount: Int,
            val collectCount: Int,
            val recommendType: RecommendType,
            val lastPlayTime: Long?,
            val nickName: String?,
            val avatar: String?,
            val categoryFullName: String?
        )
    }

}

