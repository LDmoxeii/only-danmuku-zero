package edu.only4.danmuku.domain.aggregates.video_file_upload_session

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.factory.VideoFileUploadSessionFactory

/**
 * VideoFileUploadSession aggregate wrapper
 * 视频分片上传会话; 用于跟踪预上传与分片进度
 */
class AggVideoFileUploadSession(
    payload: VideoFileUploadSessionFactory.Payload? = null,
) : Aggregate.Default<VideoFileUploadSession>(payload) {

    val id by lazy { root.id }

    class Id(key: UUID) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggVideoFileUploadSession, UUID>(key)
}

