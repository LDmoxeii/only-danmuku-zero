
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CountVideosUnderCategoriesQry {

    data class Request(
        val categoryIds: List<UUID>
    ) : RequestParam<Response>

    data class Response(
        val totalCount: Long
    )

}

