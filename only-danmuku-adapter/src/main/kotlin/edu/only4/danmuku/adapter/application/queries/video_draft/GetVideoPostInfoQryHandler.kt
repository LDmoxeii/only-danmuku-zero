package edu.only4.danmuku.adapter.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_draft.GetVideoPostInfoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoPostInfoQryHandler : Query<GetVideoPostInfoQry.Request, GetVideoPostInfoQry.Response> {

    override fun exec(request: GetVideoPostInfoQry.Request): GetVideoPostInfoQry.Response {
        return GetVideoPostInfoQry.Response(
            videoInfo = TODO("set videoInfo"),
            videoFileList = TODO("set videoFileList")
        )
    }
}
