package edu.only4.danmuku.domain.aggregates.video_play_history.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_play_history.VideoPlayHistory
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPlayHistory",
    name = "VideoPlayHistorySpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoPlayHistorySpecification : Specification<VideoPlayHistory> {

    override fun specify(entity: VideoPlayHistory): Result {
        return Result.pass()
    }
}
