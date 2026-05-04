
package edu.only4.danmuku.application.queries.user_behavior

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetRecentPasswordFailureCountQry {

    data class Request(
        val userId: UUID?,
        val loginName: String,
        val windowSeconds: Long,
        val now: Long?
    ) : RequestParam<Response>

    data class Response(
        val failureCount: Long
    )

}

