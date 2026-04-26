package edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_abnormal_operation_log")
data class UserAbnormalOperationLog(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "user_id")
    val userId: Long,
    @Column(name = "user_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user.enums.UserType.Converter::class)
    val userType: edu.only4.danmuku.domain.aggregates.user.enums.UserType,
    @Column(name = "op_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType.Converter::class)
    val opType: edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType,
    @Column(name = "ip")
    val ip: String,
    @Column(name = "occur_time")
    val occurTime: Long,
    @Column(name = "description")
    val description: String?,
    @Column(name = "extra")
    val extra: String?,
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
