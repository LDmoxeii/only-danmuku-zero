package edu.only4.danmuku.domain.aggregates.video_hls_key_token

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_hls_key_token")
data class VideoHlsKeyToken(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "video_id")
    val videoId: Long?,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "key_version")
    val keyVersion: Int,
    @Column(name = "allowed_qualities")
    val allowedQualities: String?,
    @Column(name = "token_hash")
    val tokenHash: String,
    @Column(name = "audience")
    val audience: String?,
    @Column(name = "expire_time")
    val expireTime: Long,
    @Column(name = "max_use")
    val maxUse: Int,
    @Column(name = "used_count")
    val usedCount: Int,
    @Column(name = "status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus.Converter::class)
    val status: edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus,
    @Column(name = "issue_ip")
    val issueIp: String?,
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
