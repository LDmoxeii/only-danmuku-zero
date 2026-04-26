package edu.only4.danmuku.application.queries.category

import com.only4.cap4k.ddd.core.application.RequestParam

object CategoryExistsByIdQry {

    data class Request(
        val categoryId: Long
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
