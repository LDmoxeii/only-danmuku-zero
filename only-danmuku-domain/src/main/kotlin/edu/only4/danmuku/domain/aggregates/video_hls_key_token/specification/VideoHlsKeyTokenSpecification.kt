package edu.only4.danmuku.domain.aggregates.video_hls_key_token.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoHlsKeyToken",
    name = "VideoHlsKeyTokenSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoHlsKeyTokenSpecification : Specification<VideoHlsKeyToken> {

    override fun specify(entity: VideoHlsKeyToken): Result {
        return Result.pass()
    }
}
