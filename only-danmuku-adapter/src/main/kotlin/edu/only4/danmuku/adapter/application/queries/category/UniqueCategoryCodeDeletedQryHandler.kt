package edu.only4.danmuku.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.CategoryRepository
import edu.only4.danmuku.application.queries.category.UniqueCategoryCodeDeletedQry
import edu.only4.danmuku.domain._share.meta.category.SCategory
import org.springframework.stereotype.Service

@Service
class UniqueCategoryCodeDeletedQryHandler(
    private val repository: CategoryRepository,
) : Query<UniqueCategoryCodeDeletedQry.Request, UniqueCategoryCodeDeletedQry.Response> {
    override fun exec(request: UniqueCategoryCodeDeletedQry.Request): UniqueCategoryCodeDeletedQry.Response {
        val exists = repository.exists(
            SCategory.specify { schema ->
                schema.all(
                    schema.code eq request.code,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeCategoryId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueCategoryCodeDeletedQry.Response(
            exists = exists
        )
    }
}
