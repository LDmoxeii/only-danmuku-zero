package edu.only4.danmuku.adapter.application.queries.video_post

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostVideoPostIdFileIndexDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post.SVideoFilePost
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoFilePostVideoPostIdFileIndexDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Request, UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Response> {
    override fun exec(request: UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Request): UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoFilePost::class.java)
        val specification = SVideoFilePost.specify { schema ->
            schema.all(
                schema.videoPostId eq request.videoPostId,
                schema.fileIndex eq request.fileIndex,
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

        return UniqueVideoFilePostVideoPostIdFileIndexDeletedQry.Response(
            exists = exists
        )
    }
}
