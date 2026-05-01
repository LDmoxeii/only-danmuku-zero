
package edu.only4.danmuku.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain._share.enums.PostType
import edu.only4.danmuku.domain.aggregates.video.enums.RecommendType
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoPostPageQry {

    data class Request(
        val userId: Long?,
        val categoryParentId: Long?,
        val categoryId: Long?,
        val videoNameFuzzy: String?,
        val recommendType: RecommendType?,
        val excludeVideoIds: List<Long>?
    ) : RequestParam<Response>

    data class Response(
        val videoId: Long,
        val videoCover: String?,
        val videoName: String?,
        val userId: Long?,
        val createTime: Long,
        val lastUpdateTime: Long?,
        val parentCategoryId: Long,
        val categoryId: Long?,
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
