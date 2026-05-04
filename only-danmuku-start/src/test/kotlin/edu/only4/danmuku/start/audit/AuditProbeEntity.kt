package edu.only4.danmuku.start.audit

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "audit_probe")
class AuditProbeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "create_user_id")
    var createUserId: Long? = null

    @Column(name = "create_by")
    var createBy: String? = null

    @Column(name = "create_time")
    var createTime: Long? = null

    @Column(name = "update_user_id")
    var updateUserId: Long? = null

    @Column(name = "update_by")
    var updateBy: String? = null

    @Column(name = "update_time")
    var updateTime: Long? = null
}
