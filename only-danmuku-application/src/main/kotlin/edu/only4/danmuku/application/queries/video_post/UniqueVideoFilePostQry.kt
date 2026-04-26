package edu.only4.danmuku.application.queries.video_post

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoFilePostQry {

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int,
        val excludeVideoFilePostId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
