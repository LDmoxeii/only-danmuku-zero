package edu.only4.danmuku.domain.aggregates.user_login_log

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_login_log")
data class UserLoginLog(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "user_id")
    val userId: Long?,
    @Column(name = "user_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user.enums.UserType.Converter::class)
    val userType: edu.only4.danmuku.domain.aggregates.user.enums.UserType,
    @Column(name = "login_name")
    val loginName: String,
    @Column(name = "login_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType.Converter::class)
    val loginType: edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType,
    @Column(name = "result")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult.Converter::class)
    val result: edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult,
    @Column(name = "ip")
    val ip: String,
    @Column(name = "user_agent")
    val userAgent: String?,
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
