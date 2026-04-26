package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.query.ListQueryParam

object GetVideoAllListQry {

    data class Request(
        val userId: Long
    ) : ListQueryParam<Response>

    data object Response

}
