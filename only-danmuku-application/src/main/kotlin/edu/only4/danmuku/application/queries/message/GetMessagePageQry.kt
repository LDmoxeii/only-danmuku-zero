
package edu.only4.danmuku.application.queries.message

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend

object GetMessagePageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var messageType: Int? = null
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<MessageItem>
    ) {
        data class MessageItem(
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

}
