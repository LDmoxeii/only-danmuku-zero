package edu.only4.danmuku.domain.aggregates.video_post

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain._share.enums.EncryptMethod
import edu.only4.danmuku.domain._share.enums.PostType
import edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus
import edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoAuditFailedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoAuditPassedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostCreatedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostDeletedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostInteractionChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostTranscodingRequestedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post.events.VideoPostTranscodingRequestedDomainEvent.FileItem

fun VideoPost.onCreate() {
    events().attach(this) { VideoPostCreatedDomainEvent(this) }
}

fun VideoPost.onDelete() {
    events().attach(this) { VideoPostDeletedDomainEvent(this) }
}

fun VideoPost.reviewPass() {
    if (status == VideoStatus.REVIEW_PASSED) return
    status = VideoStatus.REVIEW_PASSED
    events().attach(this) { VideoAuditPassedDomainEvent(entity = this) }
}

fun VideoPost.reviewFail() {
    if (status == VideoStatus.REVIEW_FAILED) return
    status = VideoStatus.REVIEW_FAILED
    events().attach(this) { VideoAuditFailedDomainEvent(entity = this) }
}

fun VideoPost.markPendingReview() {
    status = VideoStatus.PENDING_REVIEW
}

fun VideoPost.markTranscoding(fileList: List<VideoPostTranscodeFileSpec> = emptyList()) {
    status = VideoStatus.TRANSCODING
    if (fileList.isEmpty()) return

    events().attach(this) {
        VideoPostTranscodingRequestedDomainEvent(
            entity = this,
            videoPostId = id,
            fileList = fileList.map { spec ->
                FileItem(
                    uploadId = spec.uploadId,
                    fileIndex = spec.fileIndex,
                    fileName = spec.fileName,
                    fileSize = spec.fileSize,
                    duration = spec.duration,
                )
            },
        )
    }
}

fun VideoPost.markTranscodeFailed() {
    status = VideoStatus.TRANSCODE_FAILED
}

fun VideoPost.applyProcessStatus(targetStatus: VideoStatus) {
    when (targetStatus) {
        VideoStatus.PENDING_REVIEW -> markPendingReview()
        VideoStatus.TRANSCODING -> markTranscoding()
        VideoStatus.TRANSCODE_FAILED -> markTranscodeFailed()
        VideoStatus.REVIEW_PASSED -> reviewPass()
        VideoStatus.REVIEW_FAILED -> reviewFail()
        else -> Unit
    }
}

fun VideoPost.updateDuration(duration: Int) {
    this.duration = duration
}

fun VideoPost.applyBasicInfo(
    videoName: String? = null,
    videoCover: String? = null,
    pCategoryId: UUID? = null,
    categoryId: UUID? = null,
    postType: PostType? = null,
    originInfo: String? = null,
    tags: String? = null,
    introduction: String? = null,
    interaction: String? = null,
): Boolean {
    var changed = false
    fun <T> update(current: T, next: T?, setter: (T) -> Unit) {
        if (next != null && current != next) {
            setter(next)
            changed = true
        }
    }

    update(this.videoName, videoName) { this.videoName = it }
    update(this.videoCover, videoCover) { this.videoCover = it }
    update(this.pCategoryId, pCategoryId) { this.pCategoryId = it }
    update(this.categoryId, categoryId) { this.categoryId = it }
    update(this.postType, postType) { this.postType = it }
    update(this.originInfo, originInfo) { this.originInfo = it }
    update(this.tags, tags) { this.tags = it }
    update(this.introduction, introduction) { this.introduction = it }
    update(this.interaction, interaction) { this.interaction = it }
    return changed
}

fun VideoPost.changeInteraction(interaction: String?) {
    if (this.interaction == interaction) return
    this.interaction = interaction
    events().attach(this) { VideoPostInteractionChangedDomainEvent(this) }
}

fun videoFilePostFromSpec(
    videoPostId: UUID,
    customerId: UUID,
    spec: VideoPostTranscodeFileSpec,
): VideoFilePost {
    return VideoFilePost(
        id = UUID(0L, 0L),
        videoPostId = videoPostId,
        uploadId = spec.uploadId,
        customerId = customerId,
        fileIndex = spec.fileIndex,
        fileName = spec.fileName,
        fileSize = spec.fileSize,
        transcodeOutputPrefix = null,
        encryptOutputPrefix = null,
        transferResult = TransferResult.TRANSCODING,
        encryptStatus = EncryptStatus.UNENCRYPTED,
        encryptMethod = EncryptMethod.HLS_AES_128,
        encryptKeyVersion = null,
        duration = spec.duration,
        createUserId = null,
        createBy = null,
        createTime = null,
        updateUserId = null,
        updateBy = null,
        updateTime = null,
        deleted = 0L,
    )
}

data class VideoPostTranscodeFileSpec(
    val uploadId: UUID,
    val fileIndex: Int,
    val fileName: String?,
    val fileSize: Long?,
    val duration: Int?,
)

fun VideoFilePost.applyEncryptResult(
    success: Boolean,
    method: EncryptMethod,
    keyVersion: Int?,
    outputPrefix: String?,
) {
    encryptMethod = method
    encryptKeyVersion = keyVersion
    if (success) {
        if (!outputPrefix.isNullOrBlank()) {
            encryptOutputPrefix = outputPrefix
        }
        encryptStatus = EncryptStatus.ENCRYPTED
    } else {
        encryptStatus = EncryptStatus.FAILED
    }
}

fun VideoFilePost.applyTranscodeResult(
    outputPrefix: String?,
    duration: Int?,
    fileSize: Long?,
) {
    if (!outputPrefix.isNullOrBlank()) {
        transcodeOutputPrefix = outputPrefix
    }
    if (duration != null) {
        this.duration = duration
    }
    if (fileSize != null) {
        this.fileSize = fileSize
    }
    transferResult = TransferResult.SUCCESS
}

fun VideoFilePost.syncVariants(variants: List<VideoFilePostVariant>) {
    this.variants.clear()
    this.variants.addAll(variants)
}

