package edu.only4.danmuku.domain.aggregates.customer_video_series

import java.util.UUID

fun CustomerVideoSeries.updateBasicInfo(
    newName: String,
    newDescription: String?,
) {
    seriesName = newName
    seriesDescription = newDescription
}

fun CustomerVideoSeries.replaceVideos(
    ownerId: UUID,
    videoIds: List<UUID>,
) {
    videos.clear()
    videoIds.forEachIndexed { index, videoId ->
        videos.add(
            CustomerVideoSeriesVideo(
                id = UUID(0L, 0L),
                customerId = ownerId,
                seriesId = this.id,
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

fun CustomerVideoSeries.removeVideo(videoId: UUID): Boolean {
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

