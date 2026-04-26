package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_transcode.GetVideoFilePostPathQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoFilePostPathQryHandler : Query<GetVideoFilePostPathQry.Request, GetVideoFilePostPathQry.Response> {

    override fun exec(request: GetVideoFilePostPathQry.Request): GetVideoFilePostPathQry.Response {
        return GetVideoFilePostPathQry.Response(
            filePath = TODO("set filePath")
        )
    }
}
