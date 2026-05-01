
package edu.only4.danmuku.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetVideoDanmukuPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var videoUserId: Long? = null,
        var videoNameFuzzy: String? = null
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<DanmukuItem>
    ) {
        data class DanmukuItem(
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

}
