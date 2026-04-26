package edu.only4.danmuku.domain.aggregates.customer_message

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer_message")
data class CustomerMessage(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "video_id")
    val videoId: Long?,
    @Column(name = "message_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType.Converter::class)
    val messageType: edu.only4.danmuku.domain.aggregates.customer_message.enums.MessageType,
    @Column(name = "send_subject_id")
    val sendSubjectId: Long?,
    @Column(name = "read_type")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType.Converter::class)
    val readType: edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType,
    @Column(name = "extend_json")
    @Convert(converter = edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend.Converter::class)
    val extendJson: edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend?,
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
