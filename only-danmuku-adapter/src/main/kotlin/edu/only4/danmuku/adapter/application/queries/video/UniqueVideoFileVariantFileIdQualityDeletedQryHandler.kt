package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.UniqueVideoFileVariantFileIdQualityDeletedQry
import edu.only4.danmuku.domain._share.meta.video.SVideoFileVariant
import edu.only4.danmuku.domain.aggregates.video.VideoFileVariant
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoFileVariantFileIdQualityDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoFileVariantFileIdQualityDeletedQry.Request, UniqueVideoFileVariantFileIdQualityDeletedQry.Response> {
    override fun exec(request: UniqueVideoFileVariantFileIdQualityDeletedQry.Request): UniqueVideoFileVariantFileIdQualityDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoFileVariant::class.java)
        val specification = SVideoFileVariant.specify { schema ->
            schema.all(
                schema.fileId eq request.fileId,
                schema.quality eq request.quality,
                schema.deleted eq request.deleted,
                schema.id `neq?` request.excludeVideoFileVariantId ?: schema.id.isNotNull(),
            )
        }
        val predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        if (predicate != null) {
            criteriaQuery.where(predicate)
        }
        val exists = entityManager.createQuery(criteriaQuery).singleResult > 0L

        return UniqueVideoFileVariantFileIdQualityDeletedQry.Response(
            exists = exists
        )
    }
}
