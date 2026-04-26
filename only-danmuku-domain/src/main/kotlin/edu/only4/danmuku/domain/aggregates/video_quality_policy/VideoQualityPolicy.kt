package edu.only4.danmuku.domain.aggregates.video_quality_policy

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_quality_policy")
data class VideoQualityPolicy(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "file_index")
    val fileIndex: Int,
    @Column(name = "quality")
    val quality: String,
    @Column(name = "auth_policy")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy.Converter::class)
    val authPolicy: edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy,
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
