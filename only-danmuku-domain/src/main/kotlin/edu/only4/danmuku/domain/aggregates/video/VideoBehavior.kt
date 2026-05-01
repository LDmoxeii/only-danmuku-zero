package edu.only4.danmuku.domain.aggregates.video

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain._share.enums.PostType
import edu.only4.danmuku.domain.aggregates.video.enums.RecommendType
import edu.only4.danmuku.domain.aggregates.video.events.VideoBasicsSyncedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoCoinCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoCollectCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoCommentCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoDanmukuCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoInteractionChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoLikeCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoPlayCountDeltaAppliedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoRecommendedDomainEvent
import edu.only4.danmuku.domain.aggregates.video.events.VideoUnrecommendedDomainEvent

fun Video.toggleRecommend() {
    if (recommendType == RecommendType.RECOMMEND) {
        recommendType = RecommendType.NOT_RECOMMEND
        events().attach(this) { VideoUnrecommendedDomainEvent(entity = this) }
    } else {
        recommendType = RecommendType.RECOMMEND
        events().attach(this) { VideoRecommendedDomainEvent(entity = this) }
    }
}

fun Video.changeInteraction(newInteraction: String?) {
    interaction = newInteraction
    events().attach(this) { VideoInteractionChangedDomainEvent(this) }
}

private fun calculateDelta(current: Int, delta: Int): Pair<Int, Int> {
    if (delta == 0) return current to 0
    val updated = (current + delta).coerceAtLeast(0)
    return updated to (updated - current)
}

fun Video.applyPlayCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(playCount, delta)
    if (appliedDelta == 0) return 0
    playCount = updated
    events().attach(this) { VideoPlayCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.applyLikeCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(likeCount, delta)
    if (appliedDelta == 0) return 0
    likeCount = updated
    events().attach(this) { VideoLikeCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.applyDanmukuCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(danmukuCount, delta)
    if (appliedDelta == 0) return 0
    danmukuCount = updated
    events().attach(this) { VideoDanmukuCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.applyCommentCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(commentCount, delta)
    if (appliedDelta == 0) return 0
    commentCount = updated
    events().attach(this) { VideoCommentCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.applyCoinCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(coinCount, delta)
    if (appliedDelta == 0) return 0
    coinCount = updated
    events().attach(this) { VideoCoinCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.applyCollectCountDelta(delta: Int): Int {
    val (updated, appliedDelta) = calculateDelta(collectCount, delta)
    if (appliedDelta == 0) return 0
    collectCount = updated
    events().attach(this) { VideoCollectCountDeltaAppliedDomainEvent(this, appliedDelta) }
    return appliedDelta
}

fun Video.syncFromBasics(
    videoPostId: Long,
    customerId: Long,
    videoCover: String,
    videoName: String,
    parentCategoryId: Long,
    categoryId: Long?,
    postType: Int,
    originInfo: String?,
    tags: String?,
    introduction: String?,
    interaction: String?,
    duration: Int,
    files: List<VideoSyncFileArgs>,
) {
    this.videoPostId = videoPostId
    this.customerId = customerId
    this.videoCover = videoCover
    this.videoName = videoName
    this.pCategoryId = parentCategoryId
    this.categoryId = categoryId
    this.postType = PostType.valueOfOrNull(postType) ?: PostType.UNKNOW
    this.originInfo = originInfo
    this.tags = tags
    this.introduction = introduction
    this.interaction = interaction
    this.duration = duration
    this.recommendType = RecommendType.NOT_RECOMMEND

    this.files.clear()
    files.sortedBy { it.fileIndex }.forEach { fileArgs ->
        val file = VideoFile(
            id = 0L,
            customerId = fileArgs.customerId,
            videoId = this.id,
            videoFilePostId = fileArgs.videoFilePostId,
            fileName = fileArgs.fileName,
            fileIndex = fileArgs.fileIndex,
            fileSize = fileArgs.fileSize,
            filePath = fileArgs.filePath,
            duration = fileArgs.duration,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L
        )
        file.variants.addAll(
            fileArgs.variants.map { variant ->
                VideoFileVariant(
                    id = 0L,
                    quality = variant.quality,
                    width = variant.width,
                    height = variant.height,
                    videoBitrateKbps = variant.videoBitrateKbps,
                    audioBitrateKbps = variant.audioBitrateKbps,
                    bandwidthBps = variant.bandwidthBps,
                    playlistPath = variant.playlistPath,
                    segmentPrefix = variant.segmentPrefix,
                    segmentDuration = variant.segmentDuration,
                    createUserId = null,
                    createBy = null,
                    createTime = null,
                    updateUserId = null,
                    updateBy = null,
                    updateTime = null,
                    deleted = 0L
                )
            }
        )
        this.files.add(file)
    }

    events().attach(this) { VideoBasicsSyncedDomainEvent(entity = this) }
}

data class VideoSyncFileArgs(
    val videoFilePostId: Long,
    val customerId: Long,
    val fileName: String?,
    val fileIndex: Int,
    val fileSize: Long?,
    val filePath: String?,
    val duration: Int?,
    val variants: List<VideoSyncFileVariantArgs> = emptyList(),
)

data class VideoSyncFileVariantArgs(
    val quality: String,
    val width: Int,
    val height: Int,
    val videoBitrateKbps: Int,
    val audioBitrateKbps: Int,
    val bandwidthBps: Int,
    val playlistPath: String,
    val segmentPrefix: String?,
    val segmentDuration: Int?,
)
