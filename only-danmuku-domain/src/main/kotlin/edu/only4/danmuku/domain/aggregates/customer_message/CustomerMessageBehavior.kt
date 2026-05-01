package edu.only4.danmuku.domain.aggregates.customer_message

import edu.only4.danmuku.domain.aggregates.customer_message.enums.ReadType

fun CustomerMessage.markAsRead(now: Long? = null) {
    if (readType == ReadType.READ) return
    readType = ReadType.READ
    updateTime = now ?: (System.currentTimeMillis() / 1000)
}
