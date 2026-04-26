package edu.only4.danmuku.adapter.application.queries.video_post

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostUploadIdCustomerIdDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post.SVideoFilePost
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoFilePostUploadIdCustomerIdDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Request, UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Response> {
    override fun exec(request: UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Request): UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoFilePost::class.java)
        val specification = SVideoFilePost.specify { schema ->
            schema.all(
                schema.uploadId eq request.uploadId,
                schema.customerId eq request.customerId,
                schema.deleted eq request.deleted,
                schema.id `neq?` request.excludeVideoFilePostId ?: schema.id.isNotNull(),
            )
        }
        val predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        if (predicate != null) {
            criteriaQuery.where(predicate)
        }
        val exists = entityManager.createQuery(criteriaQuery).singleResult > 0L

        return UniqueVideoFilePostUploadIdCustomerIdDeletedQry.Response(
            exists = exists
        )
    }
}
