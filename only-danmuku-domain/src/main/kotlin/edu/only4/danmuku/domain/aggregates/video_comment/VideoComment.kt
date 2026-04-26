package edu.only4.danmuku.domain.aggregates.video_comment

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "video_comment")
data class VideoComment(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "parent_id")
    val parentId: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "video_owner_id")
    val videoOwnerId: Long,
    @Column(name = "content")
    val content: String?,
    @Column(name = "img_path")
    val imgPath: String?,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "reply_customer_id")
    val replyCustomerId: Long?,
    @Column(name = "top_type")
    val topType: Int?,
    @Column(name = "post_time")
    val postTime: Long,
    @Column(name = "like_count")
    val likeCount: Int?,
    @Column(name = "hate_count")
    val hateCount: Int?,
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
