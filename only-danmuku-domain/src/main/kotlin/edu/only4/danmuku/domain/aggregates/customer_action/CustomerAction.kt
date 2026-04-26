package edu.only4.danmuku.domain.aggregates.customer_action

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer_action")
data class CustomerAction(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_id")
    val videoId: Long,
    @Column(name = "video_owner_id")
    val videoOwnerId: Long,
    @Column(name = "comment_id")
    val commentId: Long?,
    @Column(name = "action_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType.Converter::class)
    val actionType: edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType,
    @Column(name = "action_count")
    val actionCount: Int,
    @Column(name = "action_time")
    val actionTime: Long,
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
