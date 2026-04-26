package edu.only4.danmuku.domain.aggregates.video_post_processing

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "video_post_processing_file")
class VideoPostProcessingFile(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "upload_id")
    val uploadId: Long,
    @Column(name = "transcode_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus.Converter::class)
    val transcodeStatus: edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus,
    @Column(name = "encrypt_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus.Converter::class)
    val encryptStatus: edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus,
    @Column(name = "encrypt_method")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod.Converter::class)
    val encryptMethod: edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod,
    @Column(name = "encrypt_key_version")
    val encryptKeyVersion: Int?,
    @Column(name = "transcode_output_prefix")
    val transcodeOutputPrefix: String?,
    @Column(name = "transcode_output_path")
    val transcodeOutputPath: String?,
    @Column(name = "transcode_variants_json")
    val transcodeVariantsJson: String?,
    @Column(name = "encrypt_output_dir")
    val encryptOutputDir: String?,
    @Column(name = "encrypt_output_prefix")
    val encryptOutputPrefix: String?,
    @Column(name = "duration")
    val duration: Int?,
    @Column(name = "file_size")
    val fileSize: Long?,
    @Column(name = "fail_reason")
    val failReason: String?,
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
    var videoPostProcessingVariants: List<VideoPostProcessingVariant> = emptyList()
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = false)
    lateinit var parent: VideoPostProcessing

}
