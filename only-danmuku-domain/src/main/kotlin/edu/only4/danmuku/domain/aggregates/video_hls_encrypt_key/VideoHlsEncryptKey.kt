package edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_hls_encrypt_key")
data class VideoHlsEncryptKey(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "video_id")
    val videoId: Long?,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "quality")
    val quality: String,
    @Column(name = "key_id")
    val keyId: String,
    @Column(name = "key_ciphertext")
    val keyCiphertext: String,
    @Column(name = "iv_hex")
    val ivHex: String?,
    @Column(name = "key_version")
    val keyVersion: Int,
    @Column(name = "method")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod.Converter::class)
    val method: edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod,
    @Column(name = "key_uri_template")
    val keyUriTemplate: String,
    @Column(name = "expire_time")
    val expireTime: Long?,
    @Column(name = "status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus.Converter::class)
    val status: edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus,
    @Column(name = "remark")
    val remark: String?,
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
)
