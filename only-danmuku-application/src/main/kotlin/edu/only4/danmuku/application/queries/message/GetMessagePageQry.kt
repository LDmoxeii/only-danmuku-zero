
package edu.only4.danmuku.application.queries.message

import java.util.UUID

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
            val id: UUID,
            val messageType: Int,
            val readType: Int,
            val extendJson: UserMessageExtend?,
            val createTime: Long,
            val videoPostId: UUID?,
            val videoId: UUID?,
            val videoName: String?,
            val videoCover: String?,
            val sendUserId: UUID?,
            val sendUserName: String?,
            val sendUserAvatar: String?
        )
    }

}

