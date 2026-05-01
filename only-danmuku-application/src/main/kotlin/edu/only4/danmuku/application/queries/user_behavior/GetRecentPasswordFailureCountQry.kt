
package edu.only4.danmuku.application.queries.user_behavior

import com.only4.cap4k.ddd.core.application.RequestParam

object GetRecentPasswordFailureCountQry {

    data class Request(
        val userId: Long?,
        val loginName: String,
        val windowSeconds: Long,
        val now: Long?
    ) : RequestParam<Response>

    data class Response(
        val failureCount: Long
    )

}
