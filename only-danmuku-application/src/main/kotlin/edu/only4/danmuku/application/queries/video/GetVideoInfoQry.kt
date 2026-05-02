
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain._share.enums.PostType

object GetVideoInfoQry {

    data class Request(
        val videoId: UUID
    ) : RequestParam<Response>

    data class Response(
        val videoId: UUID,
        val videoCover: String,
        val videoName: String,
        val userId: UUID,
        val createTime: Long,
        val postType: PostType,
        val originInfo: String?,
        val tags: String?,
        val introduction: String?,
        val interaction: String?,
        val playCount: Int?,
        val likeCount: Int?,
        val danmukuCount: Int?,
        val commentCount: Int?,
        val coinCount: Int?,
        val collectCount: Int?,
        val nickName: String?,
        val avatar: String?,
        val duration: Int?
    )

}

