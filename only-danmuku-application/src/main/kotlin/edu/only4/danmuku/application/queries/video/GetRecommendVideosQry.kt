
package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.RequestParam

object GetRecommendVideosQry {

    class Request : RequestParam<Response>

    data class Response(
        val videoId: Long,
        val videoCover: String?,
        val videoName: String?,
        val userId: Long,
        val nickName: String?,
        val avatar: String?,
        val playCount: Int?,
        val likeCount: Int?,
        val createTime: Long
    )

}
