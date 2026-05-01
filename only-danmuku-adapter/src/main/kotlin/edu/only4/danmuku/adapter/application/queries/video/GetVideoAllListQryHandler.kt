package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetVideoAllListQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoAllListQryHandler : Query<GetVideoAllListQry.Request, GetVideoAllListQry.Response> {

    override fun exec(request: GetVideoAllListQry.Request): GetVideoAllListQry.Response {
        return GetVideoAllListQry.Response(
            items = TODO("set items")
        )
    }
}
