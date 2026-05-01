
package edu.only4.danmuku.adapter.portal.api.payload.user_message

import edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend

object GetMessagePage {

    data class Request(
        val messageType: Int?
    )

    data class Response(
        val messageId: Long,
        val messageType: Int,
        val readType: Int,
        val extendDto: UserMessageExtend?,
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
