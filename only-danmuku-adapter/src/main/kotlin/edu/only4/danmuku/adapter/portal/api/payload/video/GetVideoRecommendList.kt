
package edu.only4.danmuku.adapter.portal.api.payload.video

object GetVideoRecommendList {

    data class Request(
        val keyword: String = "",
        val videoId: Long
    )

    data class Response(
        val videoId: Long,
        val videoCover: String?,
        val videoName: String?,
        val userId: Long?,
        val createTime: Long,
        val lastUpdateTime: Long?,
        val parentCategoryId: Long,
        val categoryId: Long?,
        val postType: Int,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val duration: Int,
        val status: Int,
        val playCount: Int,
        val likeCount: Int,
        val danmukuCount: Int,
        val commentCount: Int,
        val coinCount: Int,
        val collectCount: Int,
        val recommendType: Int,
        val lastPlayTime: Long?,
        val nickName: String?,
        val avatar: String?,
        val categoryFullName: String?
    )

}
