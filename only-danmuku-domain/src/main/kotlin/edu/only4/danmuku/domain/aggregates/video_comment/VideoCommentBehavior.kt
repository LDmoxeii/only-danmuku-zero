package edu.only4.danmuku.domain.aggregates.video_comment

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentToppedDomainEvent
import edu.only4.danmuku.domain.aggregates.video_comment.events.CommentUntoppedDomainEvent

fun VideoComment.top() {
    if (topType == 1) return
    topType = 1
    events().attach(this) { CommentToppedDomainEvent(this) }
}

fun VideoComment.untop() {
    if (topType == 0) return
    topType = 0
    events().attach(this) { CommentUntoppedDomainEvent(this) }
}

fun VideoComment.updateStatistics(likeChange: Int, hateChange: Int) {
    likeCount = ((likeCount ?: 0) + likeChange).coerceAtLeast(0)
    hateCount = ((hateCount ?: 0) + hateChange).coerceAtLeast(0)
}

fun VideoComment.isRootComment(): Boolean = parentId == 0L
