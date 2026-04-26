package edu.only4.danmuku.domain.aggregates.video.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video.Video
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "Video",
    name = "VideoSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoSpecification : Specification<Video> {

    override fun specify(entity: Video): Result {
        return Result.pass()
    }
}
