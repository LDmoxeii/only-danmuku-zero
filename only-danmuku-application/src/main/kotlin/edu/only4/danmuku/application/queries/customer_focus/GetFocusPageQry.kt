package edu.only4.danmuku.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.query.PageQueryParam

object GetFocusPageQry {

    data class Request(
        val userId: Long
    ) : PageQueryParam<Response>()

    data class Response(
        val focusUserId: Long,
        val nickName: String,
        val avatar: String?,
        val personIntroduction: String?,
        val fansCount: Int = 0,
        val haveFocus: Boolean = false,
        val focusType: Int = 0
    )

}
