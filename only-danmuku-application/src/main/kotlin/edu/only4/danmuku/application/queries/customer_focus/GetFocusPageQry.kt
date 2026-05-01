
package edu.only4.danmuku.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetFocusPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var userId: Long
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<FocusItem>
    ) {
        data class FocusItem(
            val focusUserId: Long,
            val nickName: String,
            val avatar: String?,
            val personIntroduction: String?,
            val fansCount: Int = 0,
            val haveFocus: Boolean = false,
            val focusType: Int = 0
        )
    }

}
