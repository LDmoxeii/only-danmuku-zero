package edu.only4.danmuku.adapter.portal.api.payload.video_series

import java.util.UUID

object LoadVideoSeriesWithVideo {

    data class Request(
        val userId: UUID?
    )

    data class Response(
        var list: List<Item>? = null
    )

    data class Item(
        var seriesId: String? = null,
        var seriesName: String? = null,
        var seriesDescription: String? = null,
        var sort: Int? = null,
        var videoInfoList: List<VideoInfoItem>? = null,
    )

    data class VideoInfoItem(
        var videoId: String? = null,
        var videoCover: String? = null,
        var videoName: String? = null,
        var playCount: Int? = null,
    )
}

