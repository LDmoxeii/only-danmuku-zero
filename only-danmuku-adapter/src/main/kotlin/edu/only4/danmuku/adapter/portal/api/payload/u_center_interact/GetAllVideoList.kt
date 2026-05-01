
package edu.only4.danmuku.adapter.portal.api.payload.u_center_interact

object GetAllVideoList {

    class Request

    data class Response(
        val videoId: String?,
        val videoCover: String?,
        val videoName: String?,
        val createTime: Long
    )

}
