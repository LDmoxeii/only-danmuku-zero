package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetVideoFileContextByIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoFileContextByIdQryHandler : Query<GetVideoFileContextByIdQry.Request, GetVideoFileContextByIdQry.Response> {

    override fun exec(request: GetVideoFileContextByIdQry.Request): GetVideoFileContextByIdQry.Response {
        return GetVideoFileContextByIdQry.Response(
            videoId = TODO("set videoId"),
            videoPostId = TODO("set videoPostId"),
            fileIndex = TODO("set fileIndex")
        )
    }
}
