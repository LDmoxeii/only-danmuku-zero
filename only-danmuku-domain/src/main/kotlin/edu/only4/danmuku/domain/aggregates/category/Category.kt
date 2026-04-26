package edu.only4.danmuku.domain.aggregates.category

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "category")
data class Category(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "parent_id")
    val parentId: Long,
    @Column(name = "node_path")
    val nodePath: String,
    @Column(name = "sort")
    val sort: Int,
    @Column(name = "code")
    val code: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "icon")
    val icon: String?,
    @Column(name = "background")
    val background: String?,
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
