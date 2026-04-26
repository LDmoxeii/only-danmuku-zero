package edu.only4.danmuku.domain.aggregates.video_play_history

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_play_history")
data class VideoPlayHistory(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "file_index")
    val fileIndex: Int,
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
