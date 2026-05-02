package edu.only4.danmuku.domain.aggregates.video_file_upload_session

import java.util.UUID

import com.only.engine.error.CommonErrors
import com.only.engine.exception.BusinessException
import com.only.engine.exception.RequestException
import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.UploadSessionAbortedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.VideoFileUploadSessionChunkUploadedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.events.VideoFileUploadSessionMarkedDoneDomainEvent
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors

fun VideoFileUploadSession.ensureOwnedBy(userId: UUID) {
    if (customerId != userId) {
        throw BusinessException(DanmukuBusinessErrors.OPERATION_FORBIDDEN, "没有权限操作该上传")
    }
}

fun VideoFileUploadSession.ensureActive() {
    if (status == UploadStatus.ABORTED || status == UploadStatus.EXPIRED) {
        throw BusinessException(DanmukuBusinessErrors.STATE_INVALID, "上传会话不可用")
    }
}

fun VideoFileUploadSession.ensureChunkAllowed(incomingChunkIndex: Int) {
    if (incomingChunkIndex < 0 || incomingChunkIndex > chunks - 1) {
        throw RequestException(CommonErrors.PARAM_INVALID, "分片索引非法")
    }
    if ((incomingChunkIndex - 1) > chunkIndex) {
        throw RequestException(CommonErrors.PARAM_INVALID, "分片索引非法")
    }
}

fun VideoFileUploadSession.abort(now: Long) {
    status = UploadStatus.ABORTED
    updateTime = now
    events().attach(this) { UploadSessionAbortedDomainEvent(this) }
}

fun VideoFileUploadSession.onChunkUploaded(incomingChunkIndex: Int, addedBytes: Long, now: Long) {
    if (status == UploadStatus.CREATED) {
        status = UploadStatus.UPLOADING
    }
    if (incomingChunkIndex > chunkIndex) {
        chunkIndex = incomingChunkIndex
    }
    fileSize = (fileSize ?: 0L) + addedBytes
    updateTime = now
    events().attach(this) { VideoFileUploadSessionChunkUploadedDomainEvent(this) }
}

fun VideoFileUploadSession.tryMarkDoneIfComplete() {
    if (chunkIndex >= chunks - 1) {
        status = UploadStatus.DONE
        events().attach(this) { VideoFileUploadSessionMarkedDoneDomainEvent(this) }
    }
}

fun VideoFileUploadSession.initTempAndStartUploading(tempDir: String, now: Long) {
    this.tempDir = tempDir
    if (status == UploadStatus.CREATED) {
        status = UploadStatus.UPLOADING
    }
    updateTime = now
}

