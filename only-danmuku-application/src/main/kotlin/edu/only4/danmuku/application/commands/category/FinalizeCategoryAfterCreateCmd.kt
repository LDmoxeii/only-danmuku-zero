package edu.only4.danmuku.application.commands.category

import edu.only4.danmuku.domain.aggregates.video_quality_policy.*

import edu.only4.danmuku.domain.aggregates.video_post_processing.*

import edu.only4.danmuku.domain.aggregates.video_post.*

import edu.only4.danmuku.domain.aggregates.video_play_history.*

import edu.only4.danmuku.domain.aggregates.video_hls_key_token.*

import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.*

import edu.only4.danmuku.domain.aggregates.video_danmuku.*

import edu.only4.danmuku.domain.aggregates.video_comment.*

import edu.only4.danmuku.domain.aggregates.video.*

import edu.only4.danmuku.domain.aggregates.user.*

import edu.only4.danmuku.domain.aggregates.statistics.*

import edu.only4.danmuku.domain.aggregates.video_file_upload_session.*

import edu.only4.danmuku.domain.aggregates.customer_video_series.*

import edu.only4.danmuku.domain.aggregates.customer_profile.*

import edu.only4.danmuku.domain.aggregates.customer_message.*

import edu.only4.danmuku.domain.aggregates.category.*

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.meta.category.SCategory
import org.springframework.stereotype.Service

object FinalizeCategoryAfterCreateCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val category = Mediator.repositories.findOne(
                SCategory.predicateById(request.categoryId)
            ) ?: error("Category not found: ${request.categoryId}")

            val related = Mediator.repositories.find(
                SCategory.predicate(
                    { schema ->
                        schema.all(
                            (schema.id neq request.categoryId),
                            schema.any(
                                schema.id eq category.parentId,
                                schema.parentId eq category.parentId
                            )
                        )
                    },
                    { schema -> schema.sort.asc() }
                )
            )

            val parent = related.find { it.id == category.parentId }
            val siblings = related.filter { it.parentId == category.parentId && it.id != category.id }

            val targetSort: Int = (
                if (category.sort.toInt() == 0) {
                    if (siblings.isEmpty()) 1 else siblings.maxOf { it.sort } + 1
                } else category.sort
            )

            if (siblings.isNotEmpty()) {
                val affected = siblings.filter { it.sort >= targetSort }
                affected.forEach { it.addSort(1) }
            }

            category.changeSort(targetSort)

            val parentPath = parent?.nodePath ?: ""
            category.updateNodePath(parentPath)

            Mediator.uow.save()
            return Response
        }
    }

    data class Request(
        val categoryId: Long
    ) : RequestParam<Response>

    data object Response
}
