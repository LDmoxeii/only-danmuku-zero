
package edu.only4.danmuku.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.RequestParam

object GetFansPageQry {

    data class Request(
        val userId: Long
    ) : RequestParam<Response>

    data class Response(
        val userId: Long,
        val nickName: String,
        val avatar: String?,
        val personIntroduction: String?,
        val fansCount: Int = 0,
        val haveFocus: Boolean = false,
        val focusType: Int = 0
    )

}
