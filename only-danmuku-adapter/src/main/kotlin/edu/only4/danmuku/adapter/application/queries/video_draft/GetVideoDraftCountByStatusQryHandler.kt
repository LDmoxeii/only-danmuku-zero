package edu.only4.danmuku.adapter.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_draft.GetVideoDraftCountByStatusQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoDraftCountByStatusQryHandler : Query<GetVideoDraftCountByStatusQry.Request, GetVideoDraftCountByStatusQry.Response> {

    override fun exec(request: GetVideoDraftCountByStatusQry.Request): GetVideoDraftCountByStatusQry.Response {
        return GetVideoDraftCountByStatusQry.Response(
            count = TODO("set count")
        )
    }
}
