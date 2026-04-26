package edu.only4.danmuku.application.queries.category

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueCategoryCodeQry {

    data class Request(
        val code: String,
        val excludeCategoryId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
