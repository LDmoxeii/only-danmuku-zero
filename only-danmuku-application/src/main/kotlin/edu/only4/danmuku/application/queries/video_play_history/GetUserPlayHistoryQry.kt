
package edu.only4.danmuku.application.queries.video_play_history

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetUserPlayHistoryQry {

    data class Request(
        var customerId: Long,
        override var pageNum: Int = 1,
        override var pageSize: Int = 10
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<HistoryItem>
    ) {
        data class HistoryItem(
            val historyId: Long,
            val customerId: Long,
            val videoId: Long?,
            val videoName: String?,
            val videoCover: String?,
            val fileIndex: Int,
            val playTime: Long
        )
    }

}
