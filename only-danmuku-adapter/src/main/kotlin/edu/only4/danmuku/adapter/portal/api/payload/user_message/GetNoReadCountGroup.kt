
package edu.only4.danmuku.adapter.portal.api.payload.user_message

object GetNoReadCountGroup {

    class Request

    data class Response(
        val messageType: Int,
        val messageCount: Int
    )

}
