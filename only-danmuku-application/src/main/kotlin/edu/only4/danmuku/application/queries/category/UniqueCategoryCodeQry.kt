
package edu.only4.danmuku.application.queries.category

import com.only4.cap4k.ddd.core.application.RequestParam
import java.util.UUID

object UniqueCategoryCodeQry {

    data class Request(
        val code: String,
        val excludeCategoryId: UUID?,
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
