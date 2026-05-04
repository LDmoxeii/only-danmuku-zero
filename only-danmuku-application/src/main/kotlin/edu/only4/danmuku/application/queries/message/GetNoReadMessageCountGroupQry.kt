
package edu.only4.danmuku.application.queries.message

import com.only4.cap4k.ddd.core.application.RequestParam

object GetNoReadMessageCountGroupQry {

    class Request : RequestParam<Response>

    data class Response(
        val list: List<Item>
    ) {
        data class Item(
            val messageType: Int,
            val count: Int
        )
    }

}
