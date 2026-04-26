package edu.only4.danmuku.adapter.application.queries.video_quality_policy

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.VideoQualityPolicyRepository
import edu.only4.danmuku.application.queries.video_quality_policy.UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry
import edu.only4.danmuku.domain._share.meta.video_quality_policy.SVideoQualityPolicy
import org.springframework.stereotype.Service

@Service
class UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQryHandler(
    private val repository: VideoQualityPolicyRepository,
) : Query<UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Request, UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Response> {
    override fun exec(request: UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Request): UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Response {
        val exists = repository.exists(
            SVideoQualityPolicy.specify { schema ->
                schema.all(
                    schema.videoId eq request.videoId,
                    schema.fileIndex eq request.fileIndex,
                    schema.quality eq request.quality,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeVideoQualityPolicyId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry.Response(
            exists = exists
        )
    }
}
