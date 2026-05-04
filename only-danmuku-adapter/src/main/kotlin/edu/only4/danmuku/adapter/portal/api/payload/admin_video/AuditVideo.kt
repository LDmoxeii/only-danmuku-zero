package edu.only4.danmuku.adapter.portal.api.payload.admin_video

import java.util.UUID

object AuditVideo {

    data class Request(
        val videoId: UUID,
        val status: Int,
        val reason: String?
    )
}

