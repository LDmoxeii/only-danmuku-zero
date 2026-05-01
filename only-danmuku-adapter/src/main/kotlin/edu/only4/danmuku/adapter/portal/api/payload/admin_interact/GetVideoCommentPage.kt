
package edu.only4.danmuku.adapter.portal.api.payload.admin_interact

object GetVideoCommentPage {

    data class Request(
        val videoNameFuzzy: String?
    )

    class Response

}
