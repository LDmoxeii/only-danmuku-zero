package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.ListQuery
import edu.only4.danmuku.application.queries.video.GetVideoAllListQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoAllListQryHandler : ListQuery<GetVideoAllListQry.Request, GetVideoAllListQry.Response> {

    override fun exec(request: GetVideoAllListQry.Request): List<GetVideoAllListQry.Response> {
        return emptyList()
    }
}
