package edu.only4.danmuku.application.commands.video_play_history

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
import edu.only4.danmuku.domain._share.meta.video_play_history.SVideoPlayHistory
import org.springframework.stereotype.Service

/**
 * 删除播放记录
 */
object DelHistoryCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            // 删除当前用户某个视频的播放历史（可能多条分片记录）
            Mediator.repositories.remove(
                SVideoPlayHistory.predicate { schema ->
                    schema.all(
                        schema.customerId eq request.customerId,
                        schema.videoId eq request.videoId,
                    )
                }
            )

            Mediator.uow.save()
        
            return Response
        }
    }

    data class Request(
        /** 用户ID */
        val customerId: UUID,
        /** 视频ID */
        val videoId: UUID,
    ) : RequestParam<Response>

    data object Response
}

