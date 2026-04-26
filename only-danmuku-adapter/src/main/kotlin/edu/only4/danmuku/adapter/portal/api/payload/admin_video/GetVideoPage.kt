package edu.only4.danmuku.adapter.portal.api.payload.admin_video

object GetVideoPage {

    data class Request(
        val videoNameFuzzy: String?,
        val categoryParentId: Long?,
        val categoryId: Long?,
        val recommendType: Int?
    )

    data class Response(
        val videoId: String?,
        val videoCover: String?,
        val videoName: String?,
        val userId: String?,
        val nickName: String?,
        val duration: Int?,
        val postType: Int?,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val status: Int,
        val statusName: String?,
        val createTime: Long,
        val lastUpdateTime: Long?,
        val playCount: Int?,
        val likeCount: Int?,
        val danmukuCount: Int?,
        val commentCount: Int?,
        val coinCount: Int?,
        val collectCount: Int?,
        val recommendType: Int?
    )

}
