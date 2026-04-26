package edu.only4.danmuku.application.queries.message

import com.only4.cap4k.ddd.core.application.query.PageQueryParam
import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend

object GetMessagePageQry {

    data class Request(
        val messageType: Int?
    ) : PageQueryParam<Response>()

    data class Response(
        val id: Long,
        val messageType: Int,
        val readType: Int,
        val extendJson: UserMessageExtend?,
        val createTime: Long,
        val videoPostId: Long?,
        val videoId: Long?,
        val videoName: String?,
        val videoCover: String?,
        val sendUserId: Long?,
        val sendUserName: String?,
        val sendUserAvatar: String?
    )

}
