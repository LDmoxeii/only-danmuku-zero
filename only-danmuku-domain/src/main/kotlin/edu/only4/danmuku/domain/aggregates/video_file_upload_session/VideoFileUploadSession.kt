package edu.only4.danmuku.domain.aggregates.video_file_upload_session

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_file_upload_session")
data class VideoFileUploadSession(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "file_name")
    val fileName: String,
    @Column(name = "chunks")
    val chunks: Int,
    @Column(name = "chunk_index")
    val chunkIndex: Int,
    @Column(name = "file_size")
    val fileSize: Long?,
    @Column(name = "temp_dir")
    val tempDir: String?,
    @Column(name = "status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus.Converter::class)
    val status: edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus,
    @Column(name = "duration")
    val duration: Int?,
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
    @Column(name = "expires_at")
    val expiresAt: Long?,
    @Column(name = "deleted")
    val deleted: Long
)
