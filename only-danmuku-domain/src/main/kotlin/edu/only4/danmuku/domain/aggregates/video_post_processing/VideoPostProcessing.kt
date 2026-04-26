package edu.only4.danmuku.domain.aggregates.video_post_processing

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "video_post_processing")
class VideoPostProcessing(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "total_files")
    val totalFiles: Int,
    @Column(name = "transcode_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus.Converter::class)
    val transcodeStatus: edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus,
    @Column(name = "encrypt_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus.Converter::class)
    val encryptStatus: edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus,
    @Column(name = "transcode_done_count")
    val transcodeDoneCount: Int,
    @Column(name = "encrypt_done_count")
    val encryptDoneCount: Int,
    @Column(name = "failed_count")
    val failedCount: Int,
    @Column(name = "last_fail_reason")
    val lastFailReason: String?,
    @Column(name = "create_user_id")
    val createUserId: Long?,
    @Column(name = "create_by")
    val createBy: String?,
    @Column(name = "create_time")
    val createTime: Long?,
    @Column(name = "update_user_id")
    val updateUserId: Long?,
    @Column(name = "update_by")
    val updateBy: String?,
    @Column(name = "update_time")
    val updateTime: Long?,
    @Column(name = "deleted")
    val deleted: Long
) {
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "parent_id", nullable = false)
    var files: List<VideoPostProcessingFile> = emptyList()
}
