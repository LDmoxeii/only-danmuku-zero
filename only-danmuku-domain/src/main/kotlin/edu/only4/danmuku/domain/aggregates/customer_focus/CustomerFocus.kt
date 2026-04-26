package edu.only4.danmuku.domain.aggregates.customer_focus

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer_focus")
data class CustomerFocus(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "focus_customer_id")
    val focusCustomerId: Long,
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
