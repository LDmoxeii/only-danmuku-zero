package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry
import edu.only4.danmuku.domain._share.meta.video_post_processing.SVideoPostProcessingFile
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingFile
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class UniqueVideoPostProcessingFileParentIdFileIndexDeletedQryHandler(
    private val entityManager: EntityManager,
) : Query<UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Request, UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Response> {
    override fun exec(request: UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Request): UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Response {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(VideoPostProcessingFile::class.java)
        val specification = SVideoPostProcessingFile.specify { schema ->
            schema.all(
                schema.parentId eq request.parentId,
                schema.fileIndex eq request.fileIndex,
                schema.deleted eq request.deleted,
                schema.id `neq?` request.excludeVideoPostProcessingFileId ?: schema.id.isNotNull(),
            )
        }
        val predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        if (predicate != null) {
            criteriaQuery.where(predicate)
        }
        val exists = entityManager.createQuery(criteriaQuery).singleResult > 0L

        return UniqueVideoPostProcessingFileParentIdFileIndexDeletedQry.Response(
            exists = exists
        )
    }
}
