package edu.only4.danmuku.application.queries._share.model

import java.util.UUID

import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.LogicalDeleted
import org.babyfish.jimmer.sql.MappedSuperclass

@MappedSuperclass
interface BaseEntity {
    @Id
    val id: UUID

    val createUserId: UUID?

    val createBy: String?

    val createTime: Long?

    val updateUserId: UUID?

    val updateBy: String?

    val updateTime: Long?

    @LogicalDeleted
    val deleted: Long
}

