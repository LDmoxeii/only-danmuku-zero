package edu.only4.danmuku.domain.aggregates.video_post

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
@Table(name = "video_file_post")
class VideoFilePost(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "upload_id")
    val uploadId: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "file_name")
    val fileName: String?,
    @Column(name = "file_size")
    val fileSize: Long?,
    @Column(name = "transcode_output_prefix")
    val transcodeOutputPrefix: String?,
    @Column(name = "encrypt_output_prefix")
    val encryptOutputPrefix: String?,
    @Column(name = "transfer_result")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult.Converter::class)
    val transferResult: edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult,
    @Column(name = "encrypt_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus.Converter::class)
    val encryptStatus: edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus,
    @Column(name = "encrypt_method")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod.Converter::class)
    val encryptMethod: edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod,
    @Column(name = "encrypt_key_version")
    val encryptKeyVersion: Int?,
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
    @Column(name = "deleted")
    val deleted: Long
) {
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "file_post_id", nullable = false)
    var variants: List<VideoFilePostVariant> = emptyList()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_post_id", nullable = false, insertable = false, updatable = false)
    lateinit var videoPost: VideoPost

}
