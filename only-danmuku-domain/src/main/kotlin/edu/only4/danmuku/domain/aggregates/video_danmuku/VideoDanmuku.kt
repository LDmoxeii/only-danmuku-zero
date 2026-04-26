package edu.only4.danmuku.domain.aggregates.video_danmuku

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_danmuku")
data class VideoDanmuku(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "file_id")
    val fileId: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "post_time")
    val postTime: Long?,
    @Column(name = "text")
    val text: String?,
    @Column(name = "mode")
    val mode: Int?,
    @Column(name = "color")
    val color: String?,
    @Column(name = "time")
    val time: Int?,
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
