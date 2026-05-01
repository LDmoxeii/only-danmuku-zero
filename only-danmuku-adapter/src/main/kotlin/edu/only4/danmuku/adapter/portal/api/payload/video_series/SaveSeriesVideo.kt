
package edu.only4.danmuku.adapter.portal.api.payload.video_series

object SaveSeriesVideo {

    data class Request(
        val seriesId: Long,
        val videoIds: String
    )

    class Response

}
