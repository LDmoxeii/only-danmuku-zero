
package edu.only4.danmuku.adapter.portal.api.payload.video

object GetRecommendVideoList {

    class Request

    data class Response(
        val videoId: String?,
        val videoCover: String?,
        val videoName: String?,
        val userId: String?,
        val nickName: String?,
        val avatar: String?,
        val playCount: Int?,
        val likeCount: Int?,
        val createTime: Long
    )

}
