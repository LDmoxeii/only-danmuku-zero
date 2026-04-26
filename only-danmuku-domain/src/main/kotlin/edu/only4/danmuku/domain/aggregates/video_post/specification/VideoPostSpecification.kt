package edu.only4.danmuku.domain.aggregates.video_post.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPost",
    name = "VideoPostSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoPostSpecification : Specification<VideoPost> {

    override fun specify(entity: VideoPost): Result {
        return Result.pass()
    }
}
