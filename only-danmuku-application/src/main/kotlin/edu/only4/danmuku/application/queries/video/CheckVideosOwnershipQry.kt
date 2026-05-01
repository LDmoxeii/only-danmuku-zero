
package edu.only4.danmuku.application.queries.video

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckVideosOwnershipQry {

    data class Request(
        val userId: Long,
        val videoIds: List<Long>
    ) : RequestParam<Response>

    data class Response(
        val allOwned: Boolean,
        val missing: List<Long>
    )

}
