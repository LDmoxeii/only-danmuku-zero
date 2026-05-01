
package edu.only4.danmuku.adapter.portal.api.payload.user_action

object DoAction {

    data class Request(
        val videoId: Long,
        val actionType: Int,
        val actionCount: Int = 1,
        val commentId: Long?
    )

    class Response

}
