
package edu.only4.danmuku.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoQualityAuthQry {

    data class Request(
        val videoFilePostId: Long?,
        val videoFileId: Long?
    ) : RequestParam<Response>

    data class Response(
        val policiesJson: String
    )

}
