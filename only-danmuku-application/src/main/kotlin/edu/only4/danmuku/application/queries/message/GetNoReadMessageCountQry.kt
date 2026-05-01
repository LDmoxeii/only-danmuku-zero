
package edu.only4.danmuku.application.queries.message

import com.only4.cap4k.ddd.core.application.RequestParam

object GetNoReadMessageCountQry {

    class Request : RequestParam<Response>

    data class Response(
        val count: Long = 0L
    )

}
