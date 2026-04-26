package edu.only4.danmuku.application.queries.statistics

import com.only4.cap4k.ddd.core.application.query.ListQueryParam

object GetSearchKeywordTopListQry {

    class Request : ListQueryParam<Response>

    data class Response(
        val keyword: String
    )

}
