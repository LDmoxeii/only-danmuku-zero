
package edu.only4.danmuku.application.queries.video_danmuku

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object GetDanmukuListByFileIdQry {

    data class Request(
        val fileId: UUID,
        val videoId: UUID
    ) : RequestParam<Response>

    data class Response(
        val items: List<DanmukuItem>
    ) {
        data class DanmukuItem(
            val danmukuId: UUID,
            val fileId: UUID,
            val videoId: UUID,
            val userId: UUID,
            val text: String,
            val mode: Int,
            val color: String,
            val time: Int,
            val postTime: Long,
            val videoName: String?,
            val videoCover: String?,
            val nickName: String?
        )
    }

}

