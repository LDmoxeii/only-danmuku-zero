
package edu.only4.danmuku.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetUserVideoPostQry {

    data class Request(
        val userId: Long,
        val status: VideoStatus?,
        val videoNameFuzzy: String?,
        val excludeStatusArray: List<VideoStatus>?
    ) : RequestParam<Response>

    data class Response(
        val videoPostId: Long,
        val videoId: Long?,
        val videoCover: String,
        val videoName: String,
        val duration: Int?,
        val createTime: Long,
        val lastUpdateTime: Long?,
        val status: VideoStatus,
        val interaction: String?,
        val playCount: Int? = 0,
        val likeCount: Int? = 0,
        val danmukuCount: Int? = 0,
        val commentCount: Int? = 0,
        val coinCount: Int? = 0,
        val collectCount: Int? = 0
    )

}
