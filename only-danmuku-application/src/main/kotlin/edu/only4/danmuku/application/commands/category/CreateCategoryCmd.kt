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
import edu.only4.danmuku.application.validators.CategoryMustExist
import edu.only4.danmuku.application.validators.UniqueCategoryCode
import edu.only4.danmuku.domain.aggregates.category.factory.CategoryFactory
import org.springframework.stereotype.Service

/**
 * 创建分类
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object CreateCategoryCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val initialSort: Byte = request.sort ?: 0

            Mediator.factories.create(
                CategoryFactory.Payload(
                    parentId = request.parentId,
                    code = request.code,
                    name = request.name,
                    icon = request.icon,
                    background = request.background,
                    sort = initialSort
                )
            )

            Mediator.uow.save()
        
            return Response
        }

    }

    @UniqueCategoryCode
    data class Request(
        @field:CategoryMustExist
        val parentId: Long = 0L,
        val code: String,
        val name: String,
        val icon: String? = null,
        val background: String? = null,
        val sort: Byte? = null,
    ) : RequestParam<Response>

    data object Response
}
