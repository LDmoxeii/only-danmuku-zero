package edu.only4.danmuku.domain.aggregates.video_file_upload_session.specification

import com.only4.cap4k.ddd.core.domain.aggregate.Specification
import com.only4.cap4k.ddd.core.domain.aggregate.Specification.Result
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import org.springframework.stereotype.Service

@Service
@Aggregate(
    aggregate = "VideoFileUploadSession",
    name = "VideoFileUploadSessionSpecification",
    type = Aggregate.TYPE_SPECIFICATION,
    description = ""
)
class VideoFileUploadSessionSpecification : Specification<VideoFileUploadSession> {

    override fun specify(entity: VideoFileUploadSession): Result {
        return Result.pass()
    }
}
