package edu.only4.danmuku.start.audit

import org.springframework.data.jpa.repository.JpaRepository

interface AuditProbeRepository : JpaRepository<AuditProbeEntity, Long>
