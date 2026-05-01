package edu.only4.danmuku.domain.aggregates.user

import cn.hutool.crypto.digest.BCrypt
import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.user.events.AccountDisabledDomainEvent
import edu.only4.danmuku.domain.aggregates.user.events.AccountEnabledDomainEvent
import edu.only4.danmuku.domain.aggregates.user.events.LoginInfoUpdatedDomainEvent
import edu.only4.danmuku.domain.aggregates.user.events.PasswordChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.user.events.RelationshipBoundDomainEvent
import edu.only4.danmuku.domain.aggregates.user.events.UserPhoneChangedDomainEvent

fun User.changeStatus(newStatus: Boolean) {
    val newValue = if (newStatus) 1 else 0
    if (status == newValue) return
    status = newValue
    if (newStatus) {
        events().attach(this) { AccountEnabledDomainEvent(entity = this) }
    } else {
        events().attach(this) { AccountDisabledDomainEvent(entity = this) }
    }
}

fun User.updateLoginInfo(loginTime: Long, loginIp: String) {
    lastLoginTime = loginTime
    lastLoginIp = loginIp
    events().attach(this) { LoginInfoUpdatedDomainEvent(this) }
}

fun User.bindingRelationship(relatedId: Long) {
    this.relatedId = relatedId
    events().attach(this) { RelationshipBoundDomainEvent(entity = this) }
}

fun User.verifyPassword(rawPassword: String): Boolean {
    BCrypt.hashpw(rawPassword)
    return password == rawPassword
}

fun User.changePassword(newRawPassword: String) {
    password = newRawPassword
    events().attach(this) { PasswordChangedDomainEvent(entity = this) }
}

fun User.bindPhone(phone: String) {
    if (this.phone == phone) return
    this.phone = phone
    events().attach(this) { UserPhoneChangedDomainEvent(this) }
}
