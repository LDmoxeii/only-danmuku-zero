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
import edu.only4.danmuku.domain._share.meta.category.SCategory
import jakarta.validation.constraints.NotEmpty
import org.springframework.stereotype.Service

/**
 * 更新分类排序
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object UpdateCategorySortOrderCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val categories = Mediator.repositories.find(
                SCategory.predicateByIds(request.categoryIds)
            )

            if (categories.size != request.categoryIds.toSet().size) {
                throw BusinessException(DanmukuBusinessErrors.STATE_INVALID, "存在无效的分类ID，无法完成排序")
            }

            val invalidParent = categories.any { !it.isDirectChildOf(request.parentId) }
            if (invalidParent) {
                throw BusinessException(DanmukuBusinessErrors.STATE_INVALID, "仅允许调整同一父分类下的子分类顺序")
            }

            val byId = categories.associateBy { it.id }

            var sortNo = 1
            request.categoryIds.forEach { id ->
                val category = byId[id]!!

                category.changeSort(sortNo)
                sortNo += 1
            }

            Mediator.uow.save()
        
            return Response
        }

    }

    data class Request(
        @field:CategoryMustExist
        val parentId: Long = 0L,
        @field:NotEmpty(message = "分类ID列表不能为空")
        val categoryIds: List<Long>,
    ) : RequestParam<Response>

    data object Response
}
