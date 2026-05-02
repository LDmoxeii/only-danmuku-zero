package edu.only4.danmuku.adapter.portal.api.payload.user_message

import java.util.UUID

/**
 * 删除消息接口载荷
 */
object DeleteMessage {

    data class Request(
        val messageId: UUID
    )

    class Response
}

