package edu.only4.danmuku.application.commands.video_play_history

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
import edu.only4.danmuku.domain._share.meta.video_play_history.SVideoPlayHistory
import edu.only4.danmuku.domain.aggregates.video_play_history.factory.VideoPlayHistoryFactory
import org.springframework.stereotype.Service

/**
 * 添加播放记录
 */
object AddPlayHistoryCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 查找是否已有该用户对该视频的历史记录
            val existing = Mediator.repositories.findFirst(
                SVideoPlayHistory.predicate { schema ->
                    schema.all(
                        schema.customerId eq request.customerId,
                        schema.videoId eq request.videoId,
                    )
                },
            )

            val now = System.currentTimeMillis() / 1000

            if (existing != null) {
                existing.updatePlayProgress(request.fileIndex, now)
            } else {
                Mediator.factories.create(
                    VideoPlayHistoryFactory.Payload(
                        customerId = request.customerId,
                        videoId = request.videoId,
                        fileIndex = request.fileIndex,
                    )
                )
            }

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        val customerId: Long,
        val videoId: Long,
        val fileIndex: Int,
    ) : RequestParam<Response>

    data object Response
}
