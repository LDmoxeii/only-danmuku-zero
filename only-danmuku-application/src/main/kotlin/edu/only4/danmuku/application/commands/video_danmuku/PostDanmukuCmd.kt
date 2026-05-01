package edu.only4.danmuku.application.commands.video_danmuku

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
import edu.only4.danmuku.application.validators.DanmukuInteractionAllowed
import edu.only4.danmuku.application.validators.DanmukuTextFormat
import edu.only4.danmuku.application.validators.VideoExists
import edu.only4.danmuku.domain.aggregates.video_danmuku.factory.VideoDanmukuFactory
import org.springframework.stereotype.Service

/**
 * 发送弹幕
 */
object PostDanmukuCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val now = System.currentTimeMillis() / 1000

            val payload = VideoDanmukuFactory.Payload(
                videoId = request.videoId,
                fileId = request.fileId,
                customerId = request.customerId,
                postTime = now,
                text = request.text,
                mode = request.mode != 0,
                color = request.color,
                time = request.time
            )

            Mediator.factories.create(payload)

            Mediator.uow.save()
        
            return Response
        }
    }

    @DanmukuTextFormat(modeField = "mode", timeField = "time")
    data class Request(
        @field:VideoExists
        @field:DanmukuInteractionAllowed
        val videoId: Long,
        val fileId: Long,
        val customerId: Long,
        val text: String,
        val mode: Int,
        val color: String,
        val time: Int,
    ) : RequestParam<Response>

    data object Response
}
