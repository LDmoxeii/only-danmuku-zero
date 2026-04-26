package edu.only4.danmuku.adapter.application.queries.video_post

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostVariantFilePostIdQualityDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post.SVideoFilePostVariant
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePostVariant
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoFilePostVariantFilePostIdQualityDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Request, UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Response> {
    override fun exec(request: UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Request): UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoFilePostVariant::class.java)
        val specification = SVideoFilePostVariant.specify { schema ->
            schema.all(
                schema.filePostId eq request.filePostId,
                schema.quality eq request.quality,
                schema.deleted eq request.deleted,
                schema.id `neq?` request.excludeVideoFilePostVariantId ?: schema.id.isNotNull(),
            )
        }
        val predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        if (predicate != null) {
            criteriaQuery.where(predicate)
        }
        val exists = entityManager.createQuery(criteriaQuery).singleResult > 0L

        return UniqueVideoFilePostVariantFilePostIdQualityDeletedQry.Response(
            exists = exists
        )
    }
}
