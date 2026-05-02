package edu.only4.danmuku.adapter.portal.api.payload.video_danmuku

import java.util.UUID

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

object PostDanmuku {

    data class Request(
        val videoId: UUID,
        val fileId: UUID,
        @param:NotEmpty @param:Size(max = 200) val text: String,
        val mode: Int,
        @param:NotEmpty val color: String,
        val time: Int,
    )
}

