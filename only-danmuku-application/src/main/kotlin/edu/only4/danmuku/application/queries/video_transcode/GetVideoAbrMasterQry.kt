
package edu.only4.danmuku.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoAbrMasterQry {

    data class Request(
        val fileId: Long
    ) : RequestParam<Response>

    data class Response(
        val status: String,
        val masterPath: String?
    )

}
