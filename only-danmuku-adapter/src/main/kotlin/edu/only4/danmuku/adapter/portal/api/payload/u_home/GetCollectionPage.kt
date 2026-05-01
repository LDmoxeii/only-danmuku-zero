
package edu.only4.danmuku.adapter.portal.api.payload.u_home

object GetCollectionPage {

    data class Request(
        val userId: Long?
    )

    data class Response(
        val actionId: Long,
        val videoId: String,
        val videoUserId: String,
        val commentId: Long,
        val actionType: Int,
        val actionCount: Int,
        val userId: String,
        val actionTime: Long,
        val videoName: String,
        val videoCover: String
    )

}
