package edu.only4.danmuku.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.Category
import edu.only4.danmuku.application.queries._share.model.parentId
import edu.only4.danmuku.application.queries._share.model.sort
import edu.only4.danmuku.application.queries.category.GetCategoryTreeQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * 获取分类树形结构
 */
@Service
class GetCategoryTreeQryHandler(
    private val sqlClient: KSqlClient
) : Query<GetCategoryTreeQry.Request, GetCategoryTreeQry.Response> {

    override fun exec(request: GetCategoryTreeQry.Request): GetCategoryTreeQry.Response {
        val categories = sqlClient.createQuery(Category::class) {
            orderBy(table.sort)
            select(table)
        }.execute()
        val rootId = UUID(0L, 0L)
        val childrenByParentId = categories.groupBy { it.parentId ?: rootId }

        return GetCategoryTreeQry.Response(
            items = childrenByParentId[rootId]
                .orEmpty()
                .sortedBy { it.sort }
                .map { readModelToItem(it, childrenByParentId) }
        )
    }

    private fun readModelToItem(
        dto: Category,
        childrenByParentId: Map<UUID, List<Category>>
    ): GetCategoryTreeQry.Response.CategoryItem {
        return GetCategoryTreeQry.Response.CategoryItem(
            categoryId = dto.id,
            code = dto.code,
            name = dto.name,
            parentId = dto.parentId ?: UUID(0L, 0L),
            icon = dto.icon,
            background = dto.background,
            sort = dto.sort,
            children = childrenByParentId[dto.id]
                .orEmpty()
                .sortedBy { it.sort }
                .map { readModelToChild(it, childrenByParentId) }
        )
    }

    private fun readModelToChild(
        dto: Category,
        childrenByParentId: Map<UUID, List<Category>>
    ): GetCategoryTreeQry.Response.Children {
        return GetCategoryTreeQry.Response.Children(
            categoryId = dto.id,
            code = dto.code,
            name = dto.name,
            parentId = dto.parentId ?: UUID(0L, 0L),
            icon = dto.icon,
            background = dto.background,
            sort = dto.sort,
            children = childrenByParentId[dto.id]
                .orEmpty()
                .sortedBy { it.sort }
                .map { readModelToChild(it, childrenByParentId) }
        )
    }
}
