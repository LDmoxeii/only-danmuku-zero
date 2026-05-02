package edu.only4.danmuku.adapter.portal.api.payload.video_series

import java.util.UUID

object DeleteVideoSeries {

    data class Request(
        val seriesId: UUID
    )

    class Response
}

