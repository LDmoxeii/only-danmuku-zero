
package edu.only4.danmuku.application.queries.video

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object CheckVideosOwnershipQry {

    data class Request(
        val userId: UUID,
        val videoIds: List<UUID>
    ) : RequestParam<Response>

    data class Response(
        val allOwned: Boolean,
        val missing: List<UUID>
    )

}

