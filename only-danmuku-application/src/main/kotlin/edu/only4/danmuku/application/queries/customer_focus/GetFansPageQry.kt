
package edu.only4.danmuku.application.queries.customer_focus

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetFansPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var userId: UUID
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<FansItem>
    ) {
        data class FansItem(
            val userId: UUID,
            val nickName: String,
            val avatar: String?,
            val personIntroduction: String?,
            val fansCount: Int = 0,
            val haveFocus: Boolean = false,
            val focusType: Int = 0
        )
    }

}

