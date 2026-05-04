package edu.only4.danmuku.application.commands.customer_video_series

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

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.danmuku.domain._share.meta.customer_video_series.SCustomerVideoSeries
import org.springframework.stereotype.Service

/**
 * 删除用户视频系列
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object DeleteVideoSeriesCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 删除视频系列（包括级联删除系列中的视频关联）
            // 由于配置了 orphanRemoval = true 和 CascadeType.ALL，会自动删除 customerVideoSeriesVideos
            Mediator.repositories.remove(
                SCustomerVideoSeries.predicate { schema ->
                    schema.all(
                        schema.id eq request.seriesId,
                        schema.customerId eq request.userId
                    )
                }
            )

            Mediator.uow.save()

            return Response
        }
    }

    data class Request(
        /** 用户ID */
        val userId: UUID,
        /** 系列ID */
        val seriesId: UUID
    ) : RequestParam<Response>

    data object Response
}

