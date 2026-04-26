package edu.only4.danmuku.adapter.portal.api.payload.video_series

object VideoSeriesLoad {

    data class Request(
        val userId: Long?
    )

    data class Response(
        val seriesId: String?,
        val seriesName: String?,
        val seriesDescription: String?,
        val sort: Int?,
        val videoCount: Int?,
        val cover: String?,
        val createTime: Long?,
        val updateTime: Long?
    )

}
