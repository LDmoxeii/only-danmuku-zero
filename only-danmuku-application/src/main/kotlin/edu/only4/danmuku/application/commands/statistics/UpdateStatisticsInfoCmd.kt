package edu.only4.danmuku.application.commands.statistics

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
import edu.only4.danmuku.domain._share.meta.statistics.SStatistics
import edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType
import edu.only4.danmuku.domain.aggregates.statistics.factory.StatisticsFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

/**
 * 更新统计数据
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
object UpdateStatisticsInfoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val today = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond()

            val predicate = SStatistics.predicate { schema ->
                schema.all(
                    schema.customerId.eq(request.customerId),
                    schema.dataType.eq(request.dataType),
                    schema.statisticsDate.eq(today)
                )
            }

            val statistics = Mediator.repositories.findOne(predicate)
                 ?: Mediator.factories.create(
                StatisticsFactory.Payload(
                    customerId = request.customerId,
                    dataType = request.dataType,
                    statisticsCount = 0,
                    statisticsDate = today
                )
            )

            statistics.updateCount(request.countDelta)

            Mediator.uow.save()
        
            return Response
        }

    }

    data class Request(
        val customerId: UUID,
        val dataType: StatisticsDataType,
        val countDelta: Int = 1
    ) : RequestParam<Response>


    data object Response
}

