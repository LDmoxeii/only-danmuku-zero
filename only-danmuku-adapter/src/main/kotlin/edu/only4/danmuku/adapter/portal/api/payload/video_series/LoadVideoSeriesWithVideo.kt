package edu.only4.danmuku.adapter.portal.api.payload.video_series

object LoadVideoSeriesWithVideo {

    data class Request(
        val userId: Long?
    )

    data class Response(
        val seriesId: String?,
        val seriesName: String?,
        val seriesDescription: String?,
        val sort: Int?,
        val videoInfoList: List<VideoInfoList>?
    ) {
        data class VideoInfoList(
            val videoId: String?,
            val videoCover: String?,
            val videoName: String?,
            val playCount: Int?
        )
    }

}
