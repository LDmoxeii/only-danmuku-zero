package edu.only4.danmuku.adapter.portal.api.payload.admin_video

import java.util.UUID

object DeleteVideo {

    data class Request(
        val videoId: UUID,
    )
}

