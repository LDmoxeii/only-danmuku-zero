package edu.only4.danmuku.domain.aggregates.video_post_processing

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain._share.enums.EncryptMethod
import edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus
import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingCompletedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingEncryptContextPreparedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingFileEncryptCompletedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingStartedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_post_processing.events.VideoPostProcessingTranscodeCompletedDomainEvent

fun VideoPostProcessing.appendFiles(fileList: List<VideoPostProcessingAppendFileSpec>) {
    if (fileList.isEmpty()) return

    val existing = files.map { it.fileIndex }.toMutableSet()
    val appended = fileList.filter { existing.add(it.fileIndex) }.map { spec ->
        VideoPostProcessingFile(
            id = 0L,
            fileIndex = spec.fileIndex,
            uploadId = spec.uploadId,
            transcodeStatus = ProcessStatus.PROCESSING,
            encryptStatus = ProcessStatus.PENDING,
            encryptMethod = EncryptMethod.HLS_AES_128,
            encryptKeyVersion = null,
            transcodeOutputPrefix = spec.transcodeOutputPrefix,
            transcodeOutputPath = spec.transcodeOutputPath,
            transcodeVariantsJson = null,
            encryptOutputDir = spec.encryptOutputDir,
            encryptOutputPrefix = null,
            duration = spec.duration,
            fileSize = spec.fileSize,
            failReason = null,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L,
        )
    }
    if (appended.isEmpty()) return

    files.addAll(appended)
    refreshStatus()
    events().attach(this) {
        VideoPostProcessingStartedDomainEvent(
            entity = this,
            videoPostId = videoPostId,
            fileList = appended.map { file ->
                VideoPostProcessingStartedDomainEvent.FileItem(
                    uploadId = file.uploadId,
                    fileIndex = file.fileIndex,
                    outputDir = file.transcodeOutputPath.orEmpty(),
                    objectPrefix = file.transcodeOutputPrefix.orEmpty(),
                    encOutputDir = file.encryptOutputDir.orEmpty(),
                )
            },
        )
    }
}

fun VideoPostProcessing.applyTranscodeResult(
    fileIndex: Int,
    success: Boolean,
    outputPrefix: String?,
    outputPath: String?,
    duration: Int?,
    fileSize: Long?,
    variantsJson: String?,
    failReason: String?,
    variants: List<VideoPostProcessingVariant>,
) {
    val file = getFile(fileIndex)
    file.transcodeStatus = if (success) ProcessStatus.SUCCESS else ProcessStatus.FAILED
    if (!outputPrefix.isNullOrBlank()) file.transcodeOutputPrefix = outputPrefix
    if (!outputPath.isNullOrBlank()) file.transcodeOutputPath = outputPath
    file.transcodeVariantsJson = variantsJson
    if (duration != null) file.duration = duration
    if (fileSize != null) file.fileSize = fileSize
    file.failReason = if (success) null else failReason

    if (success) {
        file.encryptStatus = ProcessStatus.PROCESSING
        file.videoPostProcessingVariants.clear()
        file.videoPostProcessingVariants.addAll(
            variants.map { variant ->
                variant.apply {
                    transcodeStatus = ProcessStatus.SUCCESS
                    encryptStatus = ProcessStatus.PENDING
                    encryptFailReason = null
                }
            },
        )
    } else if (file.encryptStatus != ProcessStatus.SKIPPED) {
        file.encryptStatus = ProcessStatus.SKIPPED
        file.videoPostProcessingVariants.clear()
    }

    refreshStatus()
    if (success) {
        events().attach(this) {
            VideoPostProcessingTranscodeCompletedDomainEvent(
                entity = this,
                videoPostId = videoPostId,
                fileIndex = fileIndex,
                outputPrefix = file.transcodeOutputPrefix,
                encOutputDir = file.encryptOutputDir,
                variantsJson = file.transcodeVariantsJson,
            )
        }
    }
}

fun VideoPostProcessing.prepareEncryptContext(
    fileIndex: Int,
    method: EncryptMethod,
    keyVersion: Int,
): VideoPostProcessingEncryptContext {
    val file = getFile(fileIndex)
    file.encryptMethod = method
    file.encryptKeyVersion = keyVersion
    events().attach(this) {
        VideoPostProcessingEncryptContextPreparedDomainEvent(
            entity = this,
            videoPostId = videoPostId,
            fileIndex = fileIndex,
            keyVersion = keyVersion,
            transcodeOutputPrefix = file.transcodeOutputPrefix,
            encryptOutputDir = file.encryptOutputDir,
            variantsJson = file.transcodeVariantsJson,
        )
    }
    return VideoPostProcessingEncryptContext(
        keyVersion = keyVersion,
        transcodeOutputPrefix = file.transcodeOutputPrefix,
        encryptOutputDir = file.encryptOutputDir,
    )
}

fun VideoPostProcessing.applyVariantEncryptResult(
    fileIndex: Int,
    quality: String,
    success: Boolean,
    method: EncryptMethod,
    keyVersion: Int,
    playlistPath: String?,
    segmentPrefix: String?,
    failReason: String?,
) {
    val file = getFile(fileIndex)
    file.encryptMethod = method
    file.encryptKeyVersion = keyVersion
    val variant = file.videoPostProcessingVariants.firstOrNull { it.quality == quality }
        ?: throw IllegalStateException("处理档位不存在: fileIndex=$fileIndex, quality=$quality")

    variant.encryptStatus = if (success) ProcessStatus.SUCCESS else ProcessStatus.FAILED
    variant.encryptFailReason = if (success) null else failReason
    if (!playlistPath.isNullOrBlank()) variant.playlistPath = playlistPath
    if (!segmentPrefix.isNullOrBlank()) variant.segmentPrefix = segmentPrefix

    if (!success) {
        file.encryptStatus = ProcessStatus.FAILED
        file.failReason = failReason
        refreshStatus()
        return
    }

    file.failReason = null
    if (file.videoPostProcessingVariants.isNotEmpty() &&
        file.videoPostProcessingVariants.all { isDone(it.encryptStatus) }
    ) {
        events().attach(this) {
            VideoPostProcessingFileEncryptCompletedDomainEvent(
                entity = this,
                videoPostId = videoPostId,
                fileIndex = fileIndex,
            )
        }
    }
    refreshStatus()
}

fun VideoPostProcessing.applyEncryptResult(
    fileIndex: Int,
    success: Boolean,
    encryptedPrefix: String?,
    failReason: String?,
) {
    val file = getFile(fileIndex)
    file.encryptStatus = if (success) ProcessStatus.SUCCESS else ProcessStatus.FAILED
    if (success && !encryptedPrefix.isNullOrBlank()) file.encryptOutputPrefix = encryptedPrefix
    file.failReason = if (success) null else failReason
    refreshStatus()

    if (isAllStepsCompleted()) {
        events().attach(this) {
            VideoPostProcessingCompletedDomainEvent(
                entity = this,
                videoPostId = videoPostId,
                duration = totalDuration(),
                failedCount = failedCount,
                lastFailReason = lastFailReason,
            )
        }
    }
}

fun VideoPostProcessing.isAllStepsCompleted(): Boolean {
    if (files.isEmpty() || failedCount > 0) return false
    return files.all { file -> isDone(file.transcodeStatus) && isDone(file.encryptStatus) }
}

fun VideoPostProcessing.totalDuration(): Int? {
    val durations = files.mapNotNull { it.duration }
    return if (durations.isEmpty()) null else durations.sum()
}

private fun VideoPostProcessing.getFile(fileIndex: Int): VideoPostProcessingFile =
    files.firstOrNull { it.fileIndex == fileIndex }
        ?: throw IllegalStateException("处理文件不存在: fileIndex=$fileIndex")

private fun VideoPostProcessing.refreshStatus() {
    totalFiles = files.size
    transcodeDoneCount = files.count { isDone(it.transcodeStatus) }
    encryptDoneCount = files.count { isDone(it.encryptStatus) }
    failedCount = files.count { it.transcodeStatus == ProcessStatus.FAILED || it.encryptStatus == ProcessStatus.FAILED }
    lastFailReason = files.mapNotNull { it.failReason?.takeIf(String::isNotBlank) }.lastOrNull()
    transcodeStatus = resolveStatus(files.map { it.transcodeStatus })
    encryptStatus = resolveStatus(files.map { it.encryptStatus })
}

private fun resolveStatus(statuses: List<ProcessStatus>): ProcessStatus {
    if (statuses.isEmpty()) return ProcessStatus.UNKNOW
    if (statuses.any { it == ProcessStatus.FAILED }) return ProcessStatus.FAILED
    if (statuses.all { it == ProcessStatus.SKIPPED }) return ProcessStatus.SKIPPED
    if (statuses.all { it == ProcessStatus.PENDING }) return ProcessStatus.PENDING
    if (statuses.all { isDone(it) }) return ProcessStatus.SUCCESS
    return ProcessStatus.PROCESSING
}

private fun isDone(status: ProcessStatus): Boolean =
    status == ProcessStatus.SUCCESS || status == ProcessStatus.SKIPPED

data class VideoPostProcessingEncryptContext(
    val keyVersion: Int,
    val transcodeOutputPrefix: String?,
    val encryptOutputDir: String?,
)

data class VideoPostProcessingAppendFileSpec(
    val uploadId: Long,
    val fileIndex: Int,
    val transcodeOutputPath: String,
    val transcodeOutputPrefix: String,
    val encryptOutputDir: String,
    val duration: Int?,
    val fileSize: Long?,
)
