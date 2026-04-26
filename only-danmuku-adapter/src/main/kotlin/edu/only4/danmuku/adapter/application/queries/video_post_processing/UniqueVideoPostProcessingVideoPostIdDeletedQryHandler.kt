package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.VideoPostProcessingRepository
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingVideoPostIdDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessing
import org.springframework.stereotype.Service

@Service
class UniqueVideoPostProcessingVideoPostIdDeletedQryHandler(
    private val repository: VideoPostProcessingRepository,
) : Query<UniqueVideoPostProcessingVideoPostIdDeletedQry.Request, UniqueVideoPostProcessingVideoPostIdDeletedQry.Response> {
    override fun exec(request: UniqueVideoPostProcessingVideoPostIdDeletedQry.Request): UniqueVideoPostProcessingVideoPostIdDeletedQry.Response {
        val exists = repository.exists(
            SVideoPostProcessing.specify { schema ->
                schema.all(
                    schema.videoPostId eq request.videoPostId,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeVideoPostProcessingId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueVideoPostProcessingVideoPostIdDeletedQry.Response(
            exists = exists
        )
    }
}
