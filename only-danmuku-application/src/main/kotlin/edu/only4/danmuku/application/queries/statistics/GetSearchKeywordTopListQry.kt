
package edu.only4.danmuku.application.queries.statistics

import com.only4.cap4k.ddd.core.application.RequestParam

object GetSearchKeywordTopListQry {

    class Request : RequestParam<Response>

    data class Response(
        val items: List<Item>
    ) {
        data class Item(
            val keyword: String
        )
    }

}
