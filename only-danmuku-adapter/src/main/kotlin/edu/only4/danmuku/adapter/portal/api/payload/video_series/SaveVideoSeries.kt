package edu.only4.danmuku.adapter.portal.api.payload.video_series

object SaveVideoSeries {

    data class Request(
        val seriesId: Long?,
        val seriesName: String,
        val seriesDescription: String?,
        val videoIds: String?
    )

    class Response

}
