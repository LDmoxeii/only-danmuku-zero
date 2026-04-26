package edu.only4.danmuku.domain.aggregates.video_audit_trace

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_audit_trace")
data class VideoAuditTrace(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_post_id")
    val videoPostId: Long,
    @Column(name = "audit_status")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus.Converter::class)
    val auditStatus: edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus,
    @Column(name = "reviewer_id")
    val reviewerId: Long?,
    @Column(name = "reviewer_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user.enums.UserType.Converter::class)
    val reviewerType: edu.only4.danmuku.domain.aggregates.user.enums.UserType,
    @Column(name = "reason")
    val reason: String?,
    @Column(name = "occur_time")
    val occurTime: Long,
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
