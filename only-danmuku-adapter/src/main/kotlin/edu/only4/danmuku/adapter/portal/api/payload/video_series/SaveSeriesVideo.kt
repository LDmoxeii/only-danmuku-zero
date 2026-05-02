package edu.only4.danmuku.adapter.portal.api.payload.video_series

import java.util.UUID

import jakarta.validation.constraints.NotEmpty

object SaveSeriesVideo {

    data class Request(
        val seriesId: UUID,
        @field:NotEmpty
        val videoIds: String,
    )

    class Response
}

