package edu.only4.danmuku.adapter.portal.api.payload.video_series

import java.util.UUID

object DeleteSeriesVideo {

    data class Request(
        val seriesId: UUID,
        val videoId: UUID,
    )

    class Response
}

