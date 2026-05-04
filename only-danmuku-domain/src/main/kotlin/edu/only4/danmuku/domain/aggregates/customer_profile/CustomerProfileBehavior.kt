package edu.only4.danmuku.domain.aggregates.customer_profile

import com.only.engine.exception.BusinessException
import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerAvatarUpdatedDomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerNicknameUpdatedDomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfileCoinsRewardedDomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfilePhoneChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfileRewardCoinsReclaimedDomainEvent
import edu.only4.danmuku.domain.aggregates.customer_profile.events.CustomerProfileUpdatedDomainEvent
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors

fun CustomerProfile.transferCoin(toProfile: CustomerProfile, amount: Int) {
    requirePositiveAmount(amount, "转账金额必须大于0")
    requireEnoughBalance(amount)
    currentCoinCount -= amount
    toProfile.currentCoinCount += amount
}

fun CustomerProfile.rewardCoins(amount: Int) {
    requirePositiveAmount(amount, "奖励数量必须大于0")
    totalCoinCount += amount
    currentCoinCount += amount
    events().attach(this) { CustomerProfileCoinsRewardedDomainEvent(this, amount) }
}

fun CustomerProfile.reclaimRewardCoins(amount: Int) {
    requireNonNegativeAmount(amount, "扣款数量不得小于0")
    if (amount == 0) return
    val deduction = amount.coerceAtMost(currentCoinCount)
    currentCoinCount -= deduction
    totalCoinCount = (totalCoinCount - deduction).coerceAtLeast(0)
    events().attach(this) { CustomerProfileRewardCoinsReclaimedDomainEvent(this, deduction) }
}

fun CustomerProfile.spendCoins(amount: Int) {
    requirePositiveAmount(amount, "扣款数量必须大于0")
    requireEnoughBalance(amount)
    currentCoinCount -= amount
}

fun CustomerProfile.updateProfileInfo(
    nickName: String? = null,
    avatar: String? = null,
    sex: SexType? = null,
    birthday: String? = null,
    school: String? = null,
    personIntroduction: String? = null,
    noticeInfo: String? = null,
    theme: ThemeType? = null,
) {
    nickName?.let {
        if (it != this.nickName) {
            this.nickName = it
            events().attach(this) { CustomerNicknameUpdatedDomainEvent(this) }
        }
    }
    avatar?.let {
        if (it != this.avatar) {
            this.avatar = it
            events().attach(this) { CustomerAvatarUpdatedDomainEvent(this) }
        }
    }
    sex?.let { this.sex = it }
    birthday?.let { this.birthday = it }
    school?.let { this.school = it }
    personIntroduction?.let { this.personIntroduction = it }
    noticeInfo?.let { this.noticeInfo = it }
    theme?.let { this.theme = it }
    events().attach(this) { CustomerProfileUpdatedDomainEvent(this) }
}

fun CustomerProfile.bindPhone(phone: String) {
    if (this.phone == phone) return
    this.phone = phone
    events().attach(this) { CustomerProfilePhoneChangedDomainEvent(this) }
}

private fun CustomerProfile.requirePositiveAmount(amount: Int, message: String) {
    if (amount <= 0) {
        throw BusinessException(DanmukuBusinessErrors.COIN_AMOUNT_INVALID, message)
    }
}

private fun CustomerProfile.requireNonNegativeAmount(amount: Int, message: String) {
    if (amount < 0) {
        throw BusinessException(DanmukuBusinessErrors.COIN_AMOUNT_INVALID, message)
    }
}

private fun CustomerProfile.requireEnoughBalance(amount: Int) {
    if (currentCoinCount < amount) {
        throw BusinessException(DanmukuBusinessErrors.COIN_BALANCE_INSUFFICIENT, "硬币余额不足")
    }
}
