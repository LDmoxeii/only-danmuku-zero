package edu.only4.danmuku.adapter.portal.api.payload.video

import java.util.UUID

object ReportVideoPlayOnline {

    data class Request(
        val fileId: UUID,
        val deviceId: String,
    )
}

