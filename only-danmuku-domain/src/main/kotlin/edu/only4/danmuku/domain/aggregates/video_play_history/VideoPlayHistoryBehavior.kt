package edu.only4.danmuku.domain.aggregates.video_play_history

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.video_play_history.events.VideoPlayHistoryProgressUpdatedDomainEvent

fun VideoPlayHistory.updatePlayProgress(newFileIndex: Int, at: Long) {
    fileIndex = newFileIndex
    updateTime = at
    events().attach(this) { VideoPlayHistoryProgressUpdatedDomainEvent(this) }
}
