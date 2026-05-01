
package edu.only4.danmuku.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.RequestParam

object GetDanmukuListByFileIdQry {

    data class Request(
        val fileId: Long,
        val videoId: Long
    ) : RequestParam<Response>

    data class Response(
        val danmukuId: Long,
        val fileId: Long,
        val videoId: Long,
        val userId: Long,
        val text: String,
        val mode: Int,
        val color: String,
        val time: Int,
        val postTime: Long,
        val videoName: String?,
        val videoCover: String?,
        val nickName: String?
    )

}
