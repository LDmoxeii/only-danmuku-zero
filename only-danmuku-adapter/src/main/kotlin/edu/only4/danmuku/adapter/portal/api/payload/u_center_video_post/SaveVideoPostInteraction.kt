package edu.only4.danmuku.adapter.portal.api.payload.u_center_video_post

import java.util.UUID

object SaveVideoPostInteraction {

    data class Request(
        val videoPostId: UUID,
        val interaction: String,
    )
}

