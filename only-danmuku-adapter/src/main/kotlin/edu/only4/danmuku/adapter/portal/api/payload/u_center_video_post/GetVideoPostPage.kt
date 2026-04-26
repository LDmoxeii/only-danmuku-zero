package edu.only4.danmuku.adapter.portal.api.payload.u_center_video_post

import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoPostPage {

    data class Request(
        val status: VideoStatus?,
        val videoNameFuzzy: String?
    )

    data class Response(
        val videoPostId: Long,
        val videoId: Long?,
        val videoCover: String?,
        val videoName: String?,
        val duration: Int?,
        val createTime: Long,
        val lastUpdateTime: Long?,
        val status: Int?,
        val statusName: String?,
        val interaction: String?,
        val playCount: Int?,
        val likeCount: Int?,
        val danmukuCount: Int?,
        val commentCount: Int?,
        val coinCount: Int?,
        val collectCount: Int?
    )

}
