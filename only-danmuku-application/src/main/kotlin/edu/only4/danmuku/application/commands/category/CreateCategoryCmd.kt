package edu.only4.danmuku.application.commands.category

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.core.domain.id.IdAllocator
import edu.only4.danmuku.application.validators.CategoryMustExist
import edu.only4.danmuku.application.validators.UniqueCategoryCode
import edu.only4.danmuku.domain._share.meta.category.SCategory
import edu.only4.danmuku.domain.aggregates.category.addSort
import edu.only4.danmuku.domain.aggregates.category.changeSort
import edu.only4.danmuku.domain.aggregates.category.factory.CategoryFactory
import edu.only4.danmuku.domain.aggregates.category.updateNodePath
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * 创建分类
 */
object CreateCategoryCmd {

    @Service
    class Handler(
        private val idAllocator: IdAllocator,
    ) : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val categoryId = idAllocator.next("uuid7", UUID::class)
            val initialSort: Byte = request.sort ?: 0
            val related = Mediator.repositories.find(
                SCategory.predicate(
                    { schema ->
                        schema.any(
                            schema.id eq request.parentId,
                            schema.parentId eq request.parentId,
                        )
                    },
                    { schema -> schema.sort.asc() },
                )
            )
            val parent = related.find { it.id == request.parentId }
            val siblings = related.filter { it.parentId == request.parentId }
            val targetSort: Int = if (initialSort.toInt() == 0) {
                if (siblings.isEmpty()) 1 else siblings.maxOf { it.sort } + 1
            } else {
                initialSort.toInt()
            }

            siblings.filter { it.sort >= targetSort }.forEach { it.addSort(1) }

            val category = Mediator.factories.create(
                CategoryFactory.Payload(
                    id = categoryId,
                    parentId = request.parentId,
                    code = request.code,
                    name = request.name,
                    icon = request.icon,
                    background = request.background,
                    sort = targetSort.toByte(),
                )
            )
            category.changeSort(targetSort)
            category.updateNodePath(parent?.nodePath.orEmpty())

            Mediator.uow.save()
            return Response
        }
    }

    @UniqueCategoryCode
    data class Request(
        @field:CategoryMustExist
        val parentId: UUID = UUID(0L, 0L),
        val code: String,
        val name: String,
        val icon: String? = null,
        val background: String? = null,
        val sort: Byte? = null,
    ) : RequestParam<Response>

    data object Response
}
