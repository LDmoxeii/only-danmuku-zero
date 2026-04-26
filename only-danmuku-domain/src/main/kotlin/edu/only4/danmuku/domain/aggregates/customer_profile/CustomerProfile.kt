package edu.only4.danmuku.domain.aggregates.customer_profile

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer_profile")
data class CustomerProfile(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "user_id")
    val userId: Long,
    @Column(name = "nick_name")
    val nickName: String,
    @Column(name = "avatar")
    val avatar: String?,
    @Column(name = "email")
    val email: String,
    @Column(name = "phone")
    val phone: String?,
    @Column(name = "sex")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType.Converter::class)
    val sex: edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType,
    @Column(name = "birthday")
    val birthday: String?,
    @Column(name = "school")
    val school: String?,
    @Column(name = "person_introduction")
    val personIntroduction: String?,
    @Column(name = "notice_info")
    val noticeInfo: String?,
    @Column(name = "total_coin_count")
    val totalCoinCount: Int,
    @Column(name = "current_coin_count")
    val currentCoinCount: Int,
    @Column(name = "theme")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType.Converter::class)
    val theme: edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType,
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
