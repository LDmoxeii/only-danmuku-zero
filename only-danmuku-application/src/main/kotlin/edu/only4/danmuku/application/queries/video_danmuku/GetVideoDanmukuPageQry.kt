
package edu.only4.danmuku.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.RequestParam

object GetVideoDanmukuPageQry {

    data class Request(
        val videoUserId: Long?,
        val videoNameFuzzy: String?
    ) : RequestParam<Response>

    data class Response(
        val danmukuId: Long,
        val videoId: Long,
        val videoName: String,
        val videoCover: String,
        val customerId: Long,
        val customerNickname: String,
        val text: String,
        val mode: Int,
        val color: String,
        val time: Int,
        val postTime: Long
    )

}
