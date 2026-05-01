
package edu.only4.danmuku.application.queries.video_file_upload_session

import com.only4.cap4k.ddd.core.application.RequestParam

object GetUploadedTempPathsQry {

    data class Request(
        val customerId: Long,
        val videoId: Long
    ) : RequestParam<Response>

    data object Response

}
