package edu.only4.danmuku.application.commands.category

import java.util.UUID

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

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import edu.only4.danmuku.domain.shared.error.DanmukuBusinessErrors
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.application.validators.CategoryMustExist
import edu.only4.danmuku.application.validators.category.unique.UniqueCategoryCode
import edu.only4.danmuku.domain._share.meta.category.SCategory
import edu.only4.danmuku.domain.aggregates.category.Category
import org.springframework.stereotype.Service

/**
 * 更新分类信息
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object UpdateCategoryInfoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val parentId = request.parentId ?: UUID(0L, 0L)
            val category = Mediator.repositories.findFirst(
                SCategory.predicateById(request.categoryId)
            ) ?: error("Category not found: ${request.categoryId}")

            category.updateBasicInfo(
                newName = request.name,
                newIcon = request.icon,
                newBackground = request.background
            )

            if (category.isParentChanged(parentId)) {
                if (category.isMovingToSelf(parentId)) {
                    throw BusinessException(DanmukuBusinessErrors.STATE_INVALID, "不能将分类移动到自身下")
                }

                val parentCategory: Category? = if (parentId != UUID(0L, 0L)) {
                    Mediator.repositories.findFirst(
                        SCategory.predicateById(parentId),
                        persist = false
                    ) ?: error("Parent category not found: ${parentId}")
                } else null

                if (parentCategory != null && category.isMovingToDescendant(parentCategory)) {
                    throw BusinessException(DanmukuBusinessErrors.STATE_INVALID, "不能将分类移动到自己的子孙节点下")
                }

                val (oldPath, newPath) = category.changeParent(
                    newParentId = parentId,
                    parentCategory = parentCategory
                )


                val descendants: List<Category> = Mediator.repositories.find(
                    SCategory.predicate { it.nodePath like "${oldPath}%" }
                )
                descendants.forEach { it.rebaseNodePath(oldPath, newPath) }
            }

            if (category.isCodeChanged(request.code)) {
                category.changeCode(newCode = request.code)
            }

            Mediator.uow.save()
        
            return Response
        }

    }

    @UniqueCategoryCode
    data class Request(
        @field:CategoryMustExist
        val categoryId: UUID,
        @field:CategoryMustExist
        val parentId: UUID? = null,
        val code: String,
        val name: String,
        val icon: String? = null,
        val background: String? = null,
    ) : RequestParam<Response>

    data object Response
}

