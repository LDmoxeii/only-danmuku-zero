package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingVariantParentIdQualityDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessingVariant
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingVariant
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoPostProcessingVariantParentIdQualityDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Request, UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Response> {
    override fun exec(request: UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Request): UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoPostProcessingVariant::class.java)
        val specification = SVideoPostProcessingVariant.specify { schema ->
            schema.all(
                schema.parentId eq request.parentId,
                schema.quality eq request.quality,
                schema.deleted eq request.deleted,
                schema.id `neq?` request.excludeVideoPostProcessingVariantId ?: schema.id.isNotNull(),
            )
        }
        val predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        if (predicate != null) {
            criteriaQuery.where(predicate)
        }
        val exists = entityManager.createQuery(criteriaQuery).singleResult > 0L

        return UniqueVideoPostProcessingVariantParentIdQualityDeletedQry.Response(
            exists = exists
        )
    }
}
