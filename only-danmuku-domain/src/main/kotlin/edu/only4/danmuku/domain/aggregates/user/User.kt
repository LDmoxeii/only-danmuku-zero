package edu.only4.danmuku.domain.aggregates.user

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.user.enums.UserType.Converter::class)
    val type: edu.only4.danmuku.domain.aggregates.user.enums.UserType,
    @Column(name = "nick_name")
    val nickName: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "phone")
    val phone: String?,
    @Column(name = "password")
    val password: String,
    @Column(name = "join_time")
    val joinTime: Long,
    @Column(name = "last_login_time")
    val lastLoginTime: Long?,
    @Column(name = "last_login_ip")
    val lastLoginIp: String?,
    @Column(name = "status")
    val status: Int,
    @Column(name = "related_id")
    val relatedId: Long?,
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
