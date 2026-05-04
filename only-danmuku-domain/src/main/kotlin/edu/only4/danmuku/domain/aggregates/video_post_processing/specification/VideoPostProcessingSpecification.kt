package edu.only4.danmuku.domain.aggregates.video_post_processing.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoPostProcessing",
    name = "VideoPostProcessingSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoPostProcessingSpecification : Specification<VideoPostProcessing> {

    override fun specify(entity: VideoPostProcessing): Result {
        return Result.pass()
    }
}
