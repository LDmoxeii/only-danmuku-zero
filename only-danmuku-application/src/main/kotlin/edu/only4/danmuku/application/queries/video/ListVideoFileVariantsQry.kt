
package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoFileVariantsQry {

    data class Request(
        val fileId: Long
    ) : RequestParam<Response>

    data class Response(
        val qualities: List<String>,
        val variantJson: String
    )

}
