package edu.only4.danmuku.domain.aggregates.customer_video_series

fun CustomerVideoSeries.updateBasicInfo(
    newName: String,
    newDescription: String?,
) {
    seriesName = newName
    seriesDescription = newDescription
}

fun CustomerVideoSeries.replaceVideos(
    ownerId: Long,
    videoIds: List<Long>,
) {
    videos.clear()
    videoIds.forEachIndexed { index, videoId ->
        videos.add(
            CustomerVideoSeriesVideo(
                id = 0L,
                customerId = ownerId,
                videoId = videoId,
                sort = index + 1,
                createUserId = null,
                createBy = null,
                createTime = null,
                updateUserId = null,
                updateBy = null,
                updateTime = null,
                deleted = 0L,
            ),
        )
    }
}

fun CustomerVideoSeries.removeVideo(videoId: Long): Boolean {
    val removed = videos.removeIf { it.videoId == videoId }
    if (removed) {
        videos.sortBy { it.sort }
        videos.forEachIndexed { index, video -> video.sort = index + 1 }
    }
    return removed
}

fun CustomerVideoSeries.updateSort(newSort: Int) {
    sort = newSort
}
