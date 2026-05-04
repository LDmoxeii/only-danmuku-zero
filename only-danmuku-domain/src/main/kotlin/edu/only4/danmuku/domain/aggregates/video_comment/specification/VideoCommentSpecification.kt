package edu.only4.danmuku.domain.aggregates.video_comment.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_comment.VideoComment
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoComment",
    name = "VideoCommentSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoCommentSpecification : Specification<VideoComment> {

    override fun specify(entity: VideoComment): Result {
        return Result.pass()
    }
}
