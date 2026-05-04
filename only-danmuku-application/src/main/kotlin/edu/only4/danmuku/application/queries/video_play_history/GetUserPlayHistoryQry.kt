
package edu.only4.danmuku.application.queries.video_play_history

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetUserPlayHistoryQry {

    data class Request(
        var customerId: UUID,
        override var pageNum: Int = 1,
        override var pageSize: Int = 10
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<HistoryItem>
    ) {
        data class HistoryItem(
            val historyId: UUID,
            val customerId: UUID,
            val videoId: UUID?,
            val videoName: String?,
            val videoCover: String?,
            val fileIndex: Int,
            val playTime: Long
        )
    }

}

